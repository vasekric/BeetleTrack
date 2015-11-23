package cz.vasekric.beetletrack.webgui.view.models;

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

    default Period getEstimatedTime() {
        if(this.hasChildrens()) {
            return getChildrens().stream()
                        .map(Issue::getEstimatedTime)
                        .reduce(Period.ZERO, Period::plus);
        }
        return Period.ZERO;
    }

    default Period getTotalSpentTime() {
        if(this.hasChildrens()) {
            return getChildrens().stream()
                        .map(Issue::getTotalSpentTime)
                        .reduce(Period.ZERO, Period::plus);
        }
        return Period.ZERO;
    }

    default boolean hasParent() {
        return this.getParent() != null;
    }

}
