package com.vinod.jpaexamples.fetchnested;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    private Long patientId;

    private String firstName;

    private Integer age;

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }
}
