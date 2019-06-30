package com.vinod.jpaexamples;

import com.vinod.jpaexamples.transactionality.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaExamplesApplication implements CommandLineRunner {

    private Logger LOGGER = LoggerFactory.getLogger(JpaExamplesApplication.class);

    @Autowired
    TransactionService transactionService;

    public static void main(String[] args) {
        SpringApplication.run(JpaExamplesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        try{
//            transactionService.methodOne();
//        }catch (Exception e){
//            LOGGER.error(e.getMessage());
//        }

    }
}
