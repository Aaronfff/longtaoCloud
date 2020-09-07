package fun.longtao.waiter;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import fun.longtao.waiter.handler.MoneyReadConverter;
import fun.longtao.waiter.integration.Barista;
import fun.longtao.waiter.mapper.auto.CoffeeMapper;
import fun.longtao.waiter.mapper.manual.CoffeeManualMapper;
import fun.longtao.waiter.model.Coffee;
import fun.longtao.waiter.model.CoffeeExample;
import fun.longtao.waiter.model.CoffeeM;
import fun.longtao.waiter.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("fun.longtao.waiter.mapper")
@RestController
@EnableCaching(proxyTargetClass = true)
@EnableBinding(Barista.class)
//@EnableTransactionManagement
public class WaiterServiceApplication implements CommandLineRunner {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new MoneyReadConverter()));
    }

//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(SqlSessionFactory dataSource){
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//    }


    @Autowired
    private CoffeeMapper coffeeMapper;
    @Autowired
    private CoffeeManualMapper coffeeManualMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;
    @Autowired
    private String testConfig;


    @Bean
    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Coffee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(WaiterServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        coffeeService.reloadCoffee();

        log.info("测试内部类类config：{}", testConfig);
        List<Coffee> allCoffee = coffeeService.findAllCoffee();
        for (Coffee coffee : allCoffee) {
            log.info("coffee name:{}", coffee.getName());
        }


        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("拿铁");
        List<Coffee> coffees = coffeeMapper.selectByExample(example);
        if (coffees != null && coffees.size() > 0) {
            Coffee coffee = coffees.get(0);
            redisTemplate.opsForValue().set(coffee.getId().toString(), coffee);
        }
    }



    @RequestMapping("/findByName")
    public Object findCoffee(@RequestParam String name, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        //使用mybatis.RowRound作为查询条件，分页不生效
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo(name);
        List<Coffee> coffees = coffeeMapper.selectByExampleWithRowbounds(example, new RowBounds(pageNum, pageSize));
        PageInfo<Coffee> coffeePageInfo = new PageInfo<>(coffees);
        log.info(coffeePageInfo.toString());

        //
        List<Coffee> allWithRowBounds = coffeeManualMapper.findAllWithRowBounds(new PageRowBounds(2, 3));
        log.info(new PageInfo<>(allWithRowBounds).toString());

        List<Coffee> allWithParam = coffeeManualMapper.findAllWithParam(pageNum, pageSize);
        log.info(new PageInfo<>(allWithParam).toString());

        return coffeePageInfo;
    }


    @RequestMapping("saveToMongodb")
    public Object saveToMongodb() {
//        Coffee coffee = coffeeMapper.selectByPrimaryKey(1L);
//        Coffee coffee = new Coffee().withName("雀巢").withPrice(Money.of(CurrencyUnit.of("CNY"), 5.0));

        CoffeeM build = CoffeeM.builder().name("雀巢").price(Money.of(CurrencyUnit.of("CNY"), 5.0)).build();
        CoffeeM save = mongoTemplate.save(build);
        log.info(save.toString());
        List<CoffeeM> coffeeMS = mongoTemplate.find(Query.query(Criteria.where("name").is("雀巢")), CoffeeM.class);

        coffeeMS.forEach(t -> log.info(t.toString()));

        return save;
    }

    @RequestMapping("testCache")
    public Object testCache() {
        return coffeeService.findAllCoffee();
    }

    @RequestMapping("testRedis")
    public Object testRedis() {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo("拿铁");
        List<Coffee> coffees = coffeeMapper.selectByExample(example);
        if (coffees != null && coffees.size() > 0) {
            Coffee coffee = coffees.get(0);
            redisTemplate.opsForValue().set(coffee.getId().toString(), coffee);
        }
        return coffees;
    }
}
