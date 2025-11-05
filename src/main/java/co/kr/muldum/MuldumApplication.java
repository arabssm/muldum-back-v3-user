package co.kr.muldum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MuldumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuldumApplication.class, args);
    }

}
