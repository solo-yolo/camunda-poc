package io.github.q1nt.inventory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryService {

    void createGenericVport(String foo) {
        log.info("creating generic vport with foo: {} in status: {}", foo, "deploying");
        doHardWork();
        log.info("generic vport successfully created");
    }

    void updateGenericVport(String foo, String status) {
        log.info("updating generic vport with foo: {} status: {}", foo, status);
        doHardWork();
        log.info("generic vport status updated");
    }

    private void doHardWork() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
