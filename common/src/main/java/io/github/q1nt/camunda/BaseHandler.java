package io.github.q1nt.camunda;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

import javax.annotation.PostConstruct;

public interface BaseHandler extends ExternalTaskHandler {

    @PostConstruct
    default void init() {
        getClient().subscribe(getTopicName()).handler(this).open();
    }

    String getTopicName();

    ExternalTaskClient getClient();

}
