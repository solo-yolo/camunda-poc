package io.github.q1nt.entrypoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "camunda", url = "${camunda.url}")
interface CamundaClient {

    @PostMapping("/engine-rest/process-definition/key/{name}/start")
    StartProcessOutput start(@PathVariable("name") String name, @RequestBody StartProcessInput input);

    default String startVportCreation(String foo) {
        return this.start("vport-creation", new StartProcessInput("foo", new Variable(foo))).getId();
    }

}

@Data
class StartProcessInput {

    private Map<String, Variable> variables;

    StartProcessInput(String name, Variable variable) {
        this.variables = new HashMap<>();
        this.variables.put(name, variable);
    }
}

@Data
class StartProcessOutput {

    private String id;
}

@Data
@AllArgsConstructor
class Variable {

    private String value;
}
