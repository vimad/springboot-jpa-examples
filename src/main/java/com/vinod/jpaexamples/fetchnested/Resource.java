package com.vinod.jpaexamples.fetchnested;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource {
    @Id
    private Long resourceId;

    private String resourceType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Hierarchy hierarchy;

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }
}
