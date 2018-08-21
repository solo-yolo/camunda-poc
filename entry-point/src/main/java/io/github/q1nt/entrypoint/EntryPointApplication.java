package io.github.q1nt.entrypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(clients = {CamundaClient.class, DbWorkerClient.class})
@SpringBootApplication
public class EntryPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntryPointApplication.class, args);
    }
}


