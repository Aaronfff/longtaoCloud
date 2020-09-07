package fun.longtao.sbaclientdemo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SbaClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbaClientDemoApplication.class, args);
    }

}
