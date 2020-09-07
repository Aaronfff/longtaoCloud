package fun.longtao.waiter.service;

import fun.longtao.waiter.mapper.auto.CoffeeMapper;
import fun.longtao.waiter.mapper.manual.CoffeeManualMapper;
import fun.longtao.waiter.model.Coffee;
import fun.longtao.waiter.model.CoffeeExample;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "coffee")
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;
    @Autowired
    private CoffeeManualMapper coffeeManualMapper;

    @Cacheable
    public List<Coffee> findAllCoffee() {
        CoffeeExample example = new CoffeeExample();
        return coffeeMapper.selectByExample(example);
    }

    @CacheEvict
    public Coffee saveCoffee(String name, Money price) {
        Coffee coffee = new Coffee().withName(name).withPrice(price);
        int i = coffeeMapper.insertSelective(coffee);
        log.info("方法返回的结果：{}", i);
        log.info("coffee 对象内的id：{}", coffee.getId());
        return coffee;
    }

    @CacheEvict
    public void reloadCoffee(){

    }

    @Cacheable
    public Coffee getCoffee(Long id) {
        return coffeeMapper.selectByPrimaryKey(id);
    }

    public Coffee getCoffee(String name) {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameEqualTo(name);
        List<Coffee> coffees = coffeeMapper.selectByExample(example);
        coffees = Optional.ofNullable(coffees).orElse(Collections.emptyList());
        return coffees.get(0);
    }

    public List<Coffee> getCoffeeByName(List<String> items) {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andNameIn(items);
        return coffeeMapper.selectByExample(example);

    }

    public List<Coffee> getByMoneyLess(Money price) {
        CoffeeExample example = new CoffeeExample();
        example.createCriteria().andPriceLessThan(price);
        return coffeeMapper.selectByExample(example);
    }
}
