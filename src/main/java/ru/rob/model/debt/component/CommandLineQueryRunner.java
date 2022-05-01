package ru.rob.model.debt.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.rob.model.debt.service.QueryService;

@Component
public class CommandLineQueryRunner implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(CommandLineQueryRunner.class);

    @Autowired
    private QueryService tryRequest;

    public void run(String... args) throws Exception {
        LOGGER.info("EXECUTING : command line runner");
//        sleep(1000);
        tryRequest.tryRequest();
    }

}
