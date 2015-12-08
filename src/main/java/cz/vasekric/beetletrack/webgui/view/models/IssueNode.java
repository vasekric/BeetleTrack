package cz.vasekric.beetletrack.webgui.view.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.Duration;
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
public class IssueNode implements Issue {
    private Integer id;
    private String name;
    private String description;
    private String tags;
    private String type;
    private User assignedTo;
    private List<Issue> childrens = new ArrayList<>();
    private Duration estimatedTime;
    private Issue parent;

    @Override
    public Duration getEstimatedTime() {
        if(estimatedTime != null) {
            return estimatedTime;
        }
        if(this.hasChildrens()) {
            return getChildrens().stream()
                    .map(Issue::getEstimatedTime)
                    .reduce(Duration.ZERO, Duration::plus);
        }
        return Duration.ZERO;
    }

    @Override
    public boolean hasChildrens() {
        return childrens != null && !childrens.isEmpty();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isNode() {
        return true;
    }

    @Override
    public Duration getTotalSpentTime() {
        if(this.hasChildrens()) {
            return getChildrens().stream()
                    .map(Issue::getTotalSpentTime)
                    .reduce(Duration.ZERO, Duration::plus);
        }
        return Duration.ZERO;
    }
}
