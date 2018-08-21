package io.github.q1nt.entrypoint;

import io.github.q1nt.VportDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RequestMapping("/api/vport")
@RestController
public class VportResource {

    private final CamundaClient camunda;
    private final DbWorkerClient dbWorker;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    void createVport(@RequestBody VportInput input) {
        log.info("received request for vport creation with foo = {}", input.getFoo());
        String processId = camunda.startVportCreation(input.getFoo());
        log.info("vport creation started, process id: {}", processId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{foo}")
    VportDetails getVportDetails(@PathVariable String foo) {
        return dbWorker.getVportDetails(foo);
    }

}
