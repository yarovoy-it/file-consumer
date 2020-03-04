package by.home.fileConsumer;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FileConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileConsumerApplication.class, args);
    }

    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
