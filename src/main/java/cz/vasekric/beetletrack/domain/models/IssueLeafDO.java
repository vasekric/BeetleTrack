package cz.vasekric.beetletrack.domain.models;

import cz.vasekric.beetletrack.webgui.view.models.Issue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private IssueDO parent;
    private List<SpendTimeDO> spentTime;
    private Period estimatedTime;


    public Period getTotalSpentTime() {
        if(spentTime == null) {
            return Period.ZERO;
        }
        return spentTime.stream()
                .map(SpendTimeDO::getTime)
                .reduce(Period.ZERO, Period::plus);
    }
}
