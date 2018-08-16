package io.github.q1nt.db;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DbWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbWorkerApplication.class, args);
    }

    @Bean
    ExternalTaskClient client(
            @Value("${spring.application.name}") String appName,
            @Value("${camunda.url}") String camundaUrl) {
        return ExternalTaskClient.create()
                .baseUrl(camundaUrl)
                .backoffStrategy(new ExponentialBackoffStrategy(500L, 2, 1000L))
                .workerId(appName)
                .build();
    }

}
