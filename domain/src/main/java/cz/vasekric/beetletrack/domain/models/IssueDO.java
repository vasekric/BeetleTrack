package cz.vasekric.beetletrack.domain.models;

import java.io.Serializable;
import java.time.Duration;

/**
 * Created by vasek on 04.10.2015.
 */
public interface IssueDO extends Serializable {
    void setId(Integer id);
    Integer getId();
    Duration getTotalSpentTime();
    Duration getEstimatedTime();
    String getName();
    String getDescription();
    IssueNodeDO getParent();
    ProjectDO getProject();
    UserDO getAssignedTo();
}

