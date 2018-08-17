package io.github.q1nt.camunda;

import lombok.AllArgsConstructor;
import org.camunda.bpm.client.backoff.BackoffStrategy;
import org.camunda.bpm.client.task.ExternalTask;

import java.util.List;

@AllArgsConstructor
public class FixedTimeBackoffStrategy implements BackoffStrategy {

    private final long time;

    @Override
    public void reconfigure(List<ExternalTask> externalTasks) {
        // no-op
    }

    @Override
    public long calculateBackoffTime() {
        return time;
    }
}
