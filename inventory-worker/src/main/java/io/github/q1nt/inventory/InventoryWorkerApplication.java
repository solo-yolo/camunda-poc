package io.github.q1nt.inventory;

import io.github.q1nt.config.ExternalTaskClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ExternalTaskClientConfig.class)
public class InventoryWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryWorkerApplication.class, args);
    }
}
