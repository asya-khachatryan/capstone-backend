package edu.aua.talents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.aua")
public class TalentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalentsApplication.class, args);
    }

}
