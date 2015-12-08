package cz.vasekric.beetletrack.domain.models;

import lombok.*;

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
public class IssueNodeDO implements IssueDO {
    private Integer id;
    private String name;
    private List<String> tags;
    private UserDO assignedTo;
    private String description;
    private IssueTypeDO type;
    private ProjectDO project;
    private IssueNodeDO parent;
    private Duration estimatedTime;
    private List<IssueDO> issues = new ArrayList<>();

    @Override
    public Duration getTotalSpentTime() {
        if(issues == null || issues.isEmpty()) {
            return Duration.ZERO;
        }
        return issues.stream()
                     .map(IssueDO::getTotalSpentTime)
                     .reduce(Duration.ZERO, Duration::plus);
    }

    @Override
    public Duration getEstimatedTime() {
        if(estimatedTime != null) {
            return estimatedTime;
        }
        if(issues == null || issues.isEmpty()) {
            return Duration.ZERO;
        }
        return issues.stream()
                .map(IssueDO::getEstimatedTime)
                .reduce(Duration.ZERO, Duration::plus);
    }

    public String getName() {
        return name;
    }
}
