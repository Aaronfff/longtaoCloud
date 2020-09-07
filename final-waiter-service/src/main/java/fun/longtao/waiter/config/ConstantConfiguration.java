package fun.longtao.waiter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ConstantConfiguration.SimpleConfiguration.class)
@ConditionalOnBean(ConstantConfiguration.ConfigFlag.class)
public class ConstantConfiguration {

    @Bean
    public String fatherConfig() {
        return "fatherConfig";
    }


    @Configuration()
//    @ConditionalOnClass(name = {"com.fasterxml.jackson.dataformat.xml.XmlMapper"})
    protected static class SimpleConfiguration {

        @Bean
        public String testConfig() {
            return "ahhahahah";
        }

        //
        @Bean
        public String innerConfig() {
            return "innerConfig";
        }
//        @Bean
//        public ConfigFlag simpleConfiguration(){
//            return new ConfigFlag();
//        }


    }

    static class ConfigFlag {

    }

}
