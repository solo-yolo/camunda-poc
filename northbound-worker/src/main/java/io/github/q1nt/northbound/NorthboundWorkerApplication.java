package io.github.q1nt.northbound;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(clients = NorthboundClient.class)
@SpringBootApplication
public class NorthboundWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthboundWorkerApplication.class, args);
    }

    @Bean
    ExternalTaskClient client(
            @Value("${spring.application.name}") String appName,
            @Value("${camunda.url}") String camundaUrl) {
        return ExternalTaskClient.create()
                .backoffStrategy(new ExponentialBackoffStrategy(500L, 2, 1000L))
                .baseUrl(camundaUrl)
                .workerId(appName)
                .build();
    }
}
