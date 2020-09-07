package fun.longtao.waiter.service;

import fun.longtao.waiter.integration.Barista;
import fun.longtao.waiter.mapper.auto.CoffeeOrderMapper;
import fun.longtao.waiter.mapper.auto.OrderMapper;
import fun.longtao.waiter.model.Coffee;
import fun.longtao.waiter.model.CoffeeOrder;
import fun.longtao.waiter.model.Order;
import fun.longtao.waiter.model.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CoffeeOrderService {
    @Autowired
    private CoffeeOrderMapper coffeeOrderMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Barista barista;


    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(String customer, Coffee[] coffeeList) {
        Order order = new Order()
                .withCustomer(customer)
                .withState(OrderStatus.INIT)
                .withTotal(calcTotal(coffeeList));
        orderMapper.insertSelective(order);

        List<CoffeeOrder> collect = Arrays.stream(coffeeList)
                .map(t -> new CoffeeOrder().withCoffeeOrderId(order.getId()).withItemsId(t.getId()))
                .collect(Collectors.toList());

        collect.forEach(coffeeOrderMapper::insertSelective);
        return order;
    }


    public boolean updateState(Order order, OrderStatus state) {
        if (order == null) {
            log.warn("Can not find order.");
            return false;
        }
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderMapper.updateByPrimaryKeySelective(order);
        log.info("Updated Order: {}", order);
        if (state == OrderStatus.PAID) {
            // 有返回值，如果要关注发送结果，则判断返回值
            // 一般消息体不会这么简单
            boolean send = barista.newOrders().send(MessageBuilder.withPayload(order.getId()).build());
            log.info("发送消息返回结果：{}", send);
        }
        return true;
    }

    private Money calcTotal(Coffee[] coffeeList) {
        List<Money> collect = Arrays.stream(coffeeList).map(Coffee::getPrice).collect(Collectors.toList());
        return Money.total(collect);
    }

    public Order get(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

}
