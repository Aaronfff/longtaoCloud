package fun.longtao.multidatasourcedemo;

import fun.longtao.multidatasourcedemo.conver.MyResultSetExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.acl.LastOwnerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})
@Slf4j
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@RestController
public class MultiDatasourceDemoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceDemoApplication.class, args);
    }

    @Autowired
    private JdbcTemplate fooJdbcTemplate;
    @Autowired
    private JdbcTemplate barJdbcTemplate;


    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDataSource(){
        DataSourceProperties fooDataSourceProperties = fooDataSourceProperties();
        log.info("数据源地址url：{}", fooDataSourceProperties.getUrl());
        return fooDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public JdbcTemplate fooJdbcTemplate(DataSource fooDataSource){
        return new JdbcTemplate(fooDataSource);
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        return new DataSourceTransactionManager(fooDataSource);
    }



    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDataSource() {
        DataSourceProperties dataSourceProperties = barDataSourceProperties();
        log.info("bar datasource: {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public JdbcTemplate barJdbcTemplate(DataSource barDataSource){
        return new JdbcTemplate(barDataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        fooJdbcTemplate.execute("CREATE TABLE aaa (ID INT IDENTITY, BAR VARCHAR(64));");
        fooJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 1,"foo_1");
        fooJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 2,"foo_2");
        fooJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 3,"foo_3");

        barJdbcTemplate.execute("CREATE TABLE aaa (ID INT IDENTITY, BAR VARCHAR(64));");
        barJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 1,"bar_1");
        barJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 2,"bar_2");
        barJdbcTemplate.update("insert into aaa(ID, BAR) values (?, ?)", 3,"bar_3");
    }

    @Transactional
    void fooInit(){

    }

    @RequestMapping("/hello")
    @Transactional(transactionManager = "fooTxManager")
    public Object hello(){
        log.info("测试事务");
        List<Map<String, Object>> query = fooJdbcTemplate.query("select * from aaa where id = 1 for update", new MyResultSetExtractor());
        fooJdbcTemplate.update("update aaa set BAR = ? where id = ?", "foo_4_foo", 1);
        if(query == null || query.size() == 0){
            log.info("查询结果为空");
            return "查询结果为空";
        }
        query.forEach(t-> log.info("id:{};bar:{}", t.get("id"), t.get("bar")));
        return query;
    }

    @RequestMapping("/test")
    @Transactional(transactionManager = "fooTxManager")
    public Object test(){
        List<Map<String, Object>> query = barJdbcTemplate.query("select * from aaa where id = 1 for update", new MyResultSetExtractor());
        barJdbcTemplate.update("update aaa set BAR = ? where id = ?", "foo_4_bar", 1);
        assert query != null;
        query.forEach(item-> log.info("id:{};bar:{}", item.get("id"), item.get("bar")));
        return query;
    }
}
