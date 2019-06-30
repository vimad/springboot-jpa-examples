package com.vinod.jpaexamples.transactionality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceWithEvents {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(String name, String email){
        Employee employee = employeeRepository.save(new Employee(name, email));
        eventPublisher.publishEvent(employee);
        return employee;
    }

    @Transactional
    public void changeStatus(Employee employee){
        employee.setIsEventTriggered(true);
    }
}
