package com.vinod.jpaexamples.transactionalitytest;

import com.vinod.jpaexamples.JpaExamplesApplication;
import com.vinod.jpaexamples.transactionality.Employee;
import com.vinod.jpaexamples.transactionality.EmployeeRepository;
import com.vinod.jpaexamples.transactionality.TransactionService;
import com.vinod.jpaexamples.transactionality.TransactionServiceWithEvents;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaExamplesApplication.class)
public class TestTransaction {
    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionServiceWithEvents transactionServiceWithEvents;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @DirtiesContext
    public void testPropagation(){
        transactionService.methodOne();
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testTransactionWithEvents(){
        final Employee employee = transactionServiceWithEvents.createEmployee("vinod", "vin@m.com");

        final Employee persistedEmployee = employeeRepository.getOne(employee.getId());

        assertEquals("vinod", persistedEmployee.getName());
        assertTrue(persistedEmployee.getIsEventTriggered());
    }
}
