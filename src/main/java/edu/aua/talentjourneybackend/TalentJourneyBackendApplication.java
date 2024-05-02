package edu.aua.talentjourneybackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"edu.aua"})
@Log4j2
public class TalentJourneyBackendApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TalentJourneyBackendApplication.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.addActiveProfile("default");
        application.setEnvironment(environment);
        application.setRegisterShutdownHook(true);
        ConfigurableApplicationContext context = application.run(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Talent Journey shutting down...");
                context.close();
            }
        });
        log.info("Talent Journey started...");
    }

}
