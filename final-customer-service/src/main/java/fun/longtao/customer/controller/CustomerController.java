package fun.longtao.customer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import fun.longtao.customer.integration.CoffeeService;
import fun.longtao.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("coffee")
@Slf4j
public class CustomerController {

    @Resource
    private CoffeeService coffeeService;

    @GetMapping(value = "/", params = "!name")
    public List<Coffee> getAll() {
        return coffeeService.getAll();
    }

    @GetMapping("/getAll")
    @SentinelResource(fallback = "fallbackCreateOrder")
    public List<Coffee> createOrder() {
        return coffeeService.getAll();
    }

    public List<Coffee> fallbackCreateOrder() {
        log.warn("Fallback to NULL order.");
        return null;
    }

}
