package com.vinod.jpaexamples.transactionality;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private Boolean isEventTriggered = false;

    public Employee(String name, String email){
        this.name = name;
        this.email = email;
    }
}
