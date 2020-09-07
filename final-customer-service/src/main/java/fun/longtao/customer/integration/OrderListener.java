package fun.longtao.customer.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {
    @StreamListener(Barista.NEW_ORDERS)
    public void listenNewOrders(Long id) {
        log.info("We've finished an order [{}].", id);
    }
}
