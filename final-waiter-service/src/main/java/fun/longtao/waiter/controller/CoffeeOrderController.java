package fun.longtao.waiter.controller;

import fun.longtao.waiter.controller.request.NewOrderRequest;
import fun.longtao.waiter.controller.request.OrderStateRequest;
import fun.longtao.waiter.model.Coffee;
import fun.longtao.waiter.model.Order;
import fun.longtao.waiter.service.CoffeeOrderService;
import fun.longtao.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;


    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }

    @PutMapping("/{id}")
    public Order updateState(@PathVariable("id") Long id,
                             @RequestBody OrderStateRequest orderState) {
        log.info("Update order state: {}", orderState);
        Order order = orderService.get(id);
        orderService.updateState(order, orderState.getState());
        return order;
    }
}
