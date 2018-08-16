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
public class CheckNorthboundServiceHandler extends BaseHandler {

    private final NorthboundClient northbound;
    private final ExternalTaskClient client;

    @Value("${topic.check-northbound}")
    private String topicName;

    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {
        String serviceId = task.getVariable("service-id");
        log.info("checking status of the service with id: {}", serviceId);
        Service northboundService = northbound.getService(serviceId);
        log.info("service status: " + northboundService.getStatus());

        Map<String, Object> variables = task.getAllVariables();
        variables.put("status", northboundService.getStatus().getValue());
        service.complete(task, variables);
    }
}
