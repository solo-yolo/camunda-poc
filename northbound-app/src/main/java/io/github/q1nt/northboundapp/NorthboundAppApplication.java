package io.github.q1nt.northboundapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class NorthboundAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthboundAppApplication.class, args);
    }

    @Autowired
    private ExecutorService executorService;

    @Bean
    ExecutorService executorService(@Value("${thread-pool-size}") int threadsCount) {
        return Executors.newFixedThreadPool(threadsCount);
    }

    @PreDestroy
    void destroy() throws InterruptedException {
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
