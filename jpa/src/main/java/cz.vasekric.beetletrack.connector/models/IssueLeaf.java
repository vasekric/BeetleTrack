package cz.vasekric.beetletrack.connector.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class IssueLeaf extends Issue {

    private String type;
    private String name;
    private String description;

    @ManyToOne
    private User assignedTo;

    @OneToMany(mappedBy = "issue")
    private List<SpendTime> spentTime;

    private Long estimatedTime;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
