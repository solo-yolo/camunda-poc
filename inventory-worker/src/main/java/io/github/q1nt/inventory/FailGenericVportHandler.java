package io.github.q1nt.inventory;

import io.github.q1nt.camunda.BaseHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class FailGenericVportHandler extends BaseHandler {

    private final InventoryService inventory;
    private final ExternalTaskClient client;

    @Value("${topic.fail-inventory-vport}")
    private String topicName;

    @Override
    public void execute(ExternalTask task, ExternalTaskService service) {
        String foo = task.getVariable("foo");
        inventory.updateGenericVport(foo, "failed");
        service.complete(task);
    }

}
