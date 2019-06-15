package com.vinod.jpaexamples.fetchnested;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hierarchy {

    @Id
    private Long hierarchyId;

    private String hierarchyType;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "parent_resource_hierarchy_id")
    private Hierarchy parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Hierarchy> children;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hierarchy", cascade = CascadeType.ALL)
    private List<Resource> resources;

    @Override
    public String toString() {
        return "Hierarchy{" +
                "hierarchyId=" + hierarchyId +
                ", hierarchyType='" + hierarchyType + '\'' +
                /*", parent=" + this.getParent() +*/
                '}';
    }
}
