package cz.vasekric.beetletrack.webgui.view.models;

import lombok.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
@RequestScoped
public class IssueLeaf implements Issue {
    private Integer id;
    private String type;
    private String name;
    private String description;
    private User assignedTo;
    private List<SpendTime> spentTime = new ArrayList<>();
    private Period estimatedTime;
    private Issue parent;

    @Override
    public Period getTotalSpentTime() {
        return spentTime.stream()
                    .map(SpendTime::getTime)
                    .reduce(Period.ZERO, Period::plus);
    }

    @Override
    public boolean hasChildrens() {
        return false;
    }

    @Override
    public List<Issue> getChildrens() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public boolean isNode() {
        return false;
    }
}
