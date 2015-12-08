package cz.vasekric.beetletrack.domain.models;

import cz.vasekric.beetletrack.webgui.view.models.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Period;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueLeafDO implements IssueDO {
    private Integer id;
    private IssueTypeDO type;
    private String name;
    private String description;
    private UserDO assignedTo;
    private ProjectDO project;
    private IssueNodeDO parent;
    private List<SpendTimeDO> spentTime;
    private Duration estimatedTime;


    public Duration getTotalSpentTime() {
        if(spentTime == null) {
            return Duration.ZERO;
        }
        return spentTime.stream()
                .map(SpendTimeDO::getTime)
                .reduce(Duration.ZERO, Duration::plus);
    }
}
