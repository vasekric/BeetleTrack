package cz.vasekric.beetletrack.domain.models;

import java.time.Period;

/**
 * Created by vasek on 04.10.2015.
 */
public interface IssueDO {
    void setId(Integer id);
    Integer getId();
    Period getTotalSpentTime();
    Period getEstimatedTime();
    String getName();
}

