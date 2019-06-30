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

    @Autowired
    TransactionServiceWithEvents transactionServiceWithEvents;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public void testTrans(){
        transactionService.methodOne();
    }

    @GetMapping("testEvents")
    public Employee testTransWithEvents(){
        final Employee employee = transactionServiceWithEvents.createEmployee("vinod", "vin@m.com");

        final Employee persistedEmployee = employeeRepository.getOne(employee.getId());

        return persistedEmployee;
    }
}
