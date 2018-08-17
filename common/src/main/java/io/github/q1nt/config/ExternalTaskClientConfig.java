package io.github.q1nt.config;

import io.github.q1nt.camunda.FixedTimeBackoffStrategy;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.BackoffStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalTaskClientConfig {

    @Bean
    ExternalTaskClient client(
            @Value("${spring.application.name}") String appName,
            @Value("${camunda.url}") String camundaUrl,
            BackoffStrategy backoffStrategy) {
        return ExternalTaskClient.create()
                .baseUrl(camundaUrl)
                .backoffStrategy(backoffStrategy)
                .workerId(appName)
                .build();
    }

    @Bean
    BackoffStrategy backoffStrategy(
            @Value("${camunda.poll-interval:1000}") long time) {
        return new FixedTimeBackoffStrategy(time);
    }

}
