package ru.rob.model.debt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author eugenBoris
 * @version 0.001
 */
@SpringBootApplication
public class SpringBootConsoleApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        logger.info(" -> STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class);
        logger.info(" -> APPLICATION FINISHED");
    }

}
