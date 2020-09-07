package fun.longtao.customer.integration;

import fun.longtao.customer.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class CoffeeFallbackService implements CoffeeService {
    @Override
    public List<Coffee> getAll() {
        log.error("请求waiter-service失败");
        return Collections.emptyList();
    }
}
