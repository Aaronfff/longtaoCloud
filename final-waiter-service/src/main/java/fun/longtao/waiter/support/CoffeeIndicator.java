package fun.longtao.waiter.support;

import fun.longtao.waiter.model.Coffee;
import fun.longtao.waiter.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoffeeIndicator implements HealthIndicator {

    @Autowired
    private CoffeeService coffeeService;

    @Override
    public Health health() {
        List<Coffee> allCoffee = coffeeService.findAllCoffee();
        int size = allCoffee.size();
        Health health;
        if (size <= 0) {
            health = Health.down().withDetail("count", size).withDetail("detail", "未初始化咖啡").build();
        } else {
            health = Health.up().withDetail("count", size).withDetail("detail", "已初始化咖啡").build();
        }

        return health;
    }
}
