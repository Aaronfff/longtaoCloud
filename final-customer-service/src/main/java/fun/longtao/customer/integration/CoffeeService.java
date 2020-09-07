package fun.longtao.customer.integration;

import fun.longtao.customer.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee",
        qualifier = "coffeeService", path = "/coffee", fallback = CoffeeFallbackService.class)
public interface CoffeeService {

    @GetMapping("/")
    List<Coffee> getAll();

}
