package com.vinod.jpaexamples.transactionality;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class EmployeeCreatedEventListener {

    private Logger logger = LoggerFactory.getLogger(EmployeeCreatedEventListener.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TransactionServiceWithEvents transactionServiceWithEvents;

    //*******************************************************************************//
    //This triggers within the current transaction
//    @EventListener
//    public void processEmployeeCreatedEvent(Employee employee){
//        logger.info("Normal event triggered");
//        employee.setIsEventTriggered(true);
//        // throw new RuntimeException();
//    }

    //******************************************************************************//
    // This happens with a transaction but in a different thread
    // When errors are thrown in this it will not roll back the commits.
    // By default this is triggered after the commit is done in transaction
    @TransactionalEventListener
    public void processEventWIthTransaction(Employee employee){
        logger.info("Transactional event triggered");
        transactionServiceWithEvents.changeStatus(employee);
        throw new RuntimeException();
    }
}
