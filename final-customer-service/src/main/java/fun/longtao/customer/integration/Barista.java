package fun.longtao.customer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String NEW_ORDERS = "newOrders";
    String FINISHED_ORDERS = "finishedOrders";

    @Input(NEW_ORDERS)
    SubscribableChannel finishedOrders();
}
