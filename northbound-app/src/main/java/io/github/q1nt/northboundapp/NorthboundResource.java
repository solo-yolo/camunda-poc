package io.github.q1nt.northboundapp;

import static java.util.Optional.ofNullable;
import static org.springframework.http.ResponseEntity.notFound;

import io.github.q1nt.northbound.CreateServiceInput;
import io.github.q1nt.northbound.CreateServiceOutput;
import io.github.q1nt.northbound.Service;
import io.github.q1nt.northbound.ServiceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
@RestController
@RequestMapping("/northbound")
public class NorthboundResource {

    private final Map<String, Service> data = new HashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/service")
    CreateServiceOutput createService(@RequestBody CreateServiceInput input) {
        log.info("creating service with foo: {}", input.getFoo());
        String id = UUID.randomUUID().toString();
        Service service = new Service(input.getFoo(), ServiceStatus.IN_PROGRESS);
        data.put(id, service);
        executor.submit(new ServiceCreationJob(id));
        return new CreateServiceOutput(id);
    }

    @GetMapping("/service/{id}")
    ResponseEntity<Service> getService(@PathVariable("id") String serviceId) {
        return ofNullable(data.get(serviceId))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

    class ServiceCreationJob implements Runnable {

        private static final int MIN_WAIT_TIME = 15;
        private static final int MAX_WAIT_TIME = 15;

        private final String id;
        private final int wait;
        private final Random random;

        ServiceCreationJob(String id) {
            this.id = id;
            this.random = new Random();
            this.wait = random.nextInt(MAX_WAIT_TIME) + MIN_WAIT_TIME;
            log.info("will wait {} for completion", wait);
        }

        @Override
        public void run() {
            log.info("service {} creation  started", id);
            try {
                Thread.sleep(wait * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("service creation finished");
            ServiceStatus status = random.nextBoolean() ? ServiceStatus.CREATED : ServiceStatus.FAILED;
            data.get(id).setStatus(status);
            log.info("service id: {} - status: {}", id, status);
        }
    }
}