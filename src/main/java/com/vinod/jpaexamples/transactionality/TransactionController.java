package com.vinod.jpaexamples.transactionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testTransaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public void testTrans(){
        transactionService.methodOne();
    }
}
