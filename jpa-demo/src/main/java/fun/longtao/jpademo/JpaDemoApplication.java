package fun.longtao.jpademo;

import com.fasterxml.jackson.databind.ser.Serializers;
import fun.longtao.jpademo.model.BaseEntity;
import fun.longtao.jpademo.model.Coffee;
import fun.longtao.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
@RestController
public class JpaDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;


    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Coffee coffee1 = Coffee.builder().name("naTie").price(Money.of(CurrencyUnit.of("CNY"), 30.0)).build();
        Coffee coffee2 = Coffee.builder().name("NATIE").price(Money.of(CurrencyUnit.of("CNY"), 10.0)).build();
        coffeeRepository.saveAll(new ArrayList<>(Arrays.asList(coffee1, coffee2)));

        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(t -> log.info(t.toString()));

        List<Coffee> result1 = coffeeRepository.findByName("拿铁");
        log.info(result1.toString());

        List<Coffee> result2 = coffeeRepository.findByNameEndingWithAndPriceIsAfter("铁", Money.of(CurrencyUnit.of("CNY"), 30.0));
        log.info(result2.toString());
    }

    @RequestMapping("upadate")
    public Object updateCoffee(@RequestParam Long id, Double price, String name) {

        if (id == null) {
            return "传入id不能为空";
        }
        log.info("请求参数：id:{},price:{},name:{}", id, price, name);
        List<Coffee> byName = coffeeRepository.findByName(name);
        Coffee coffee = byName.get(0);
        log.info(coffee.toString());
        coffee.setPrice(Money.of(CurrencyUnit.of("CNY"), price));
        coffeeRepository.save(coffee);
        return coffee;
    }

    @RequestMapping("select")
    public Object selectCoffee(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id == null && name == null) {
            return "参数错误";
        }
        ArrayList<String> list = new ArrayList<>();
        for (String s : list) {

        }

        HashMap<String, String> map = new HashMap<>();

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase(false));

        Optional<Coffee> natie = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build()));
        log.info(natie.toString());

        if (id != null) {
            return coffeeRepository.findById(id);
        } else {
            return coffeeRepository.findByName(name).get(0);
        }
    }
}
