package fun.longtao.customer;

import fun.longtao.customer.integration.Barista;
import fun.longtao.customer.model.Coffee;
import fun.longtao.customer.support.OrderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Slf4j
@EnableDiscoveryClient
@EnableBinding(Barista.class)
@EnableFeignClients
@EnableCircuitBreaker
public class CustomerServiceApplication implements CommandLineRunner {

    @Autowired
    private RestTemplate nacosRestTemplate;
    @Autowired
    private RestTemplate simpleRestTemplate;

    @Value("${order.discount}")
    private Long discount;

    @Autowired
    private OrderProperties orderProperties;

    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
//                .sources(NacosDemoApplication.class)
//                .bannerMode(Banner.Mode.OFF)
////                .web(WebApplicationType.NONE)
//                .run(args);
        SpringApplication.run(CustomerServiceApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        log.info("订单折扣：{}", discount);
        log.info("订单折扣：{}", orderProperties.getDiscount());
        readMenu();
    }

    private void readMenu() {
//        ParameterizedTypeReference<List<Coffee>> ptr =
//                new ParameterizedTypeReference<List<Coffee>>() {
//                };
//        ResponseEntity<List<Coffee>> list = simpleRestTemplate
//                .exchange("http://localhost:8080/coffee/", HttpMethod.GET, null, ptr);
//        list.getBody().forEach(c -> log.info("Coffee: {}", c));
    }


//    @Bean
//    public HttpComponentsClientHttpRequestFactory requestFactory() {
//        PoolingHttpClientConnectionManager connectionManager =
//                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
//        connectionManager.setMaxTotal(200);
//        connectionManager.setDefaultMaxPerRoute(20);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setConnectionManager(connectionManager)
//                .evictIdleConnections(30, TimeUnit.SECONDS)
//                .disableAutomaticRetries()
//                // 有 Keep-Alive 认里面的值，没有的话永久有效
//                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
//                // 换成自定义的
////                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return requestFactory;
//    }

    @Bean
    @LoadBalanced
    public RestTemplate nacosRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RestTemplate simpleRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
