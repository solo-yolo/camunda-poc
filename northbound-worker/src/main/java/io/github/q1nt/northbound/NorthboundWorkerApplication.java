package io.github.q1nt.northbound;

import io.github.q1nt.config.ExternalTaskClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients(clients = NorthboundClient.class)
@SpringBootApplication
@Import(ExternalTaskClientConfig.class)
public class NorthboundWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthboundWorkerApplication.class, args);
    }
}
