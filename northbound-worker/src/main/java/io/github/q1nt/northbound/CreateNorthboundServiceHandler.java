package io.github.q1nt.northbound;

import io.github.q1nt.camunda.BaseHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Getter
@Component
public class CreateNorthboundServiceHandler extends BaseHandler {

    private final NorthboundClient northbound;
    private final ExternalTaskClient client;

    @Value("${topic.create-northbound}")
    private String topicName;

    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {
        String foo = task.getVariable("foo");
        log.info("creating service in northbound with foo: {}", foo);
        String id = northbound.createService(foo);
        log.info("successfully created northbound service, service id: {}", id);

        Map<String, Object> variables = task.getAllVariables();
        variables.put("service-id", id);
        service.complete(task, variables);
    }
}
