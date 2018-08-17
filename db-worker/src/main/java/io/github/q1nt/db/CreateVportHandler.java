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

@Slf4j
@RequiredArgsConstructor
@Getter
@Component
public class CreateVportHandler implements BaseHandler {

    private final VportRepository repository;
    private final ExternalTaskClient client;

    @Value("${topic.create-db-vport}")
    private String topicName;

    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {
        String foo = task.getVariable("foo");
        String status = "creating";

        log.info("creating vport with foo: {} in status {}", foo, status);
        repository.saveAndFlush(new Vport(foo, status));
        log.info("vport created");
        service.complete(task);
    }
}
