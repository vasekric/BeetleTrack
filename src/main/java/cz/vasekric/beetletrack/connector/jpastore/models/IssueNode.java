package cz.vasekric.beetletrack.connector.jpastore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Period;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Entity
public class IssueNode extends Issue {
    private String type;
    private String name;
    private String description;

    @ManyToOne
    private User assignedTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Issue> childrens;
}
