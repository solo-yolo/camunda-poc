package io.github.q1nt.northbound;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "northbound", url = "${northbound.url}", path = "/northbound")
public interface NorthboundClient {

    @PostMapping("/service")
    CreateServiceOutput createService(@RequestBody CreateServiceInput input);

    @GetMapping("/service/{id}")
    Service getService(@PathVariable("id") String serviceId);

    default String createService(String foo) {
        CreateServiceInput input = new CreateServiceInput();
        input.setFoo(foo);
        return this.createService(input).getId();
    }


}
