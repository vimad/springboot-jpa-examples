package com.vinod.jpaexamples.fetchnested;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.ReservationPatients", attributeNodes = @NamedAttributeNode("patients")),
        @NamedEntityGraph(name = "graph.ReservationResources", attributeNodes = @NamedAttributeNode("resources")),
        @NamedEntityGraph(
                name = "graph.ReservationResourcesHierarchy",
                attributeNodes = @NamedAttributeNode(value = "resources", subgraph = "resources"),
                subgraphs = @NamedSubgraph(name = "resources", attributeNodes = @NamedAttributeNode("hierarchy"))
        ),
        @NamedEntityGraph(
                name = "graph.all",
                attributeNodes = {@NamedAttributeNode(value = "resources", subgraph = "resources"),
                                  @NamedAttributeNode("patients")},
                subgraphs = @NamedSubgraph(name = "resources", attributeNodes = @NamedAttributeNode("hierarchy"))
        )
})
public class Reservation {
    @Id
    private Long reservationId;

    private String category;

    @OneToMany
    @JoinColumn(name = "reservation_id")
    private Set<Patient> patients;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    private Set<Resource> resources;

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", category='" + category + "\'}";
    }
}
