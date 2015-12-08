package cz.vasekric.beetletrack.connector.jpastore.models;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueTypeDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import lombok.*;

import javax.persistence.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IssueNode extends Issue {
    private String type;
    private String name;
    private String description;

    @ManyToOne
    private User assignedTo;

    @OneToMany(mappedBy = "parent")
    private List<Issue> childrens;

    private Long estimatedTime;
}
