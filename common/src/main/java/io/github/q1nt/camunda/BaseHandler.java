package io.github.q1nt.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import javax.annotation.PostConstruct;

// todo: convert to interface
@Slf4j
public abstract class BaseHandler implements ExternalTaskHandler {

    @PostConstruct
    public void init() {
        getClient()
                .subscribe(getTopicName())
                .handler(this)
                .open();
        log.info("successfully subscribed handler to topic {}", getTopicName());
    }

    public abstract String getTopicName();

    public abstract ExternalTaskClient getClient();

}
