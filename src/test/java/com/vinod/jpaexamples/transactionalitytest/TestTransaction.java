package com.vinod.jpaexamples.transactionalitytest;

import com.vinod.jpaexamples.JpaExamplesApplication;
import com.vinod.jpaexamples.transactionality.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaExamplesApplication.class)
public class TestTransaction {
    @Autowired
    TransactionService transactionService;

    @Test
    @DirtiesContext
    public void testPropagation(){
        transactionService.methodOne();
    }
}
