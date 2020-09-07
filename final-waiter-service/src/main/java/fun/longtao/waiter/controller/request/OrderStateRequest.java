package fun.longtao.waiter.controller.request;

import fun.longtao.waiter.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderStatus state;
}
