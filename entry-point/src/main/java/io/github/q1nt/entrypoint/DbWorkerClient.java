package io.github.q1nt.entrypoint;

import io.github.q1nt.VportDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "db-worker", url = "${db-worker.url}", decode404 = true)
public interface DbWorkerClient {

    @GetMapping("/vport/{foo}")
    ResponseEntity<VportDetails> getVportDetails(@PathVariable("foo") String foo);

}
