package cz.vasekric.beetletrack.connector.jpastore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Period;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */

@Getter
@Setter
@Entity
public class IssueLeaf extends Issue {

    private String type;
    private String name;
    private String description;

    @ManyToOne
    private User assignedTo;

    @OneToMany(mappedBy = "issue")
    private List<SpendTime> spentTime;

    private Period estimatedTime;
}
