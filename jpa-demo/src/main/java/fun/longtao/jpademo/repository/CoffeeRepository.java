package fun.longtao.jpademo.repository;

import fun.longtao.jpademo.model.Coffee;
import org.joda.money.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    @Query("select c from #{#entityName} c where c.name = ?1")
    List<Coffee> findByName(String name);

    List<Coffee> findByNameEndingWithAndPriceIsAfter(String name, Money price);
}
