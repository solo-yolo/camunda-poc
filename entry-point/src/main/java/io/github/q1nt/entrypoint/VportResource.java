package io.github.q1nt.entrypoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class VportResource {

    private final CamundaClient camunda;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/vport")
    void createVport(@RequestBody VportInput input) {
        log.info("received request for vport creation with foo = {}", input.getFoo());
        String processId = camunda.startVportCreation(input.getFoo());
        log.info("vport creation started, process id: {}", processId);
    }

}
