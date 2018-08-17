package io.github.q1nt.db;

import io.github.q1nt.camunda.BaseHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Getter
@Component
public class VportCreatedHandler implements BaseHandler {

    private final VportRepository repository;
    private final ExternalTaskClient client;

    @Value("${topic.db-vport-created}")
    private String topicName;

    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {
        log.info("vport creation finished");

        String foo = task.getVariable("foo");
        String status = "deployed";

        log.info("marking vport with foo: {} as {}", foo, status);
        Optional<Vport> optional = repository.findById(foo);
        if (optional.isPresent()) {
            Vport vport = optional.get();
            vport.setStatus(status);
            repository.save(vport);
            log.info("vport marked as created!");
            service.complete(task);
        } else {
            log.error("vport with foo {} was not found", foo);
            service.handleBpmnError(task, "no-such-vport");
        }

    }
}
