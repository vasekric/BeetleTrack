package cz.vasekric.beetletrack.domain.models;

import lombok.*;

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
public class IssueNodeDO implements IssueDO {
    private Integer id;
    private IssueTypeDO type;
    private String name;
    private List<String> tags;
    private String description;
    private UserDO assignedTo;
    private ProjectDO project;
    private IssueDO parent;
    private List<IssueDO> issues = new ArrayList<>();
    private Period estimatedTime;

    @Override
    public Period getTotalSpentTime() {
        if(issues == null || issues.isEmpty()) {
            return Period.ZERO;
        }
        return issues.stream()
                     .map(IssueDO::getTotalSpentTime)
                     .reduce(Period.ZERO, Period::plus);
    }

    @Override
    public Period getEstimatedTime() {
        if(estimatedTime != null) {
            return estimatedTime;
        }
        if(issues == null || issues.isEmpty()) {
            return Period.ZERO;
        }
        return issues.stream()
                .map(IssueDO::getEstimatedTime)
                .reduce(Period.ZERO, Period::plus);
    }

    public String getName() {
        return name;
    }
}
