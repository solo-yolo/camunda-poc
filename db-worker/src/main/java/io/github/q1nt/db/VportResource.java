package io.github.q1nt.db;

import static org.springframework.http.ResponseEntity.notFound;

import io.github.q1nt.VportDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class VportResource {

    private final VportRepository repository;

    @GetMapping("/vport/{foo}")
    ResponseEntity<VportDetails> getDetails(@PathVariable String foo) {
        return repository.findById(foo)
                .map(vport -> new VportDetails(vport.getFoo(), vport.getStatus()))
                .map(ResponseEntity::ok)
                .orElse(notFound().build());
    }

}
