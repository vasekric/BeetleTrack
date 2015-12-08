package cz.vasekric.beetletrack.webgui.view.models;

import java.time.Duration;
import java.time.Period;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
public interface Issue {
    String getName();
    void setName(String name);
    boolean hasChildrens();
    List<Issue> getChildrens();
    boolean isLeaf();
    boolean isNode();
    Issue getParent();
    String getDescription();
    User getAssignedTo();

    Duration getEstimatedTime();

    Duration getTotalSpentTime();

    default boolean hasParent() {
        return this.getParent() != null;
    }

}
