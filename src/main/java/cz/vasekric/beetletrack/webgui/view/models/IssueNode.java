package cz.vasekric.beetletrack.webgui.view.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class IssueNode implements Issue {
    private Integer id;
    private String name;
    private String description;
    private String tags;
    private String type;
    private User assignedTo;
    private List<Issue> childrens = new ArrayList<>();
    private Period estimatedTime;
    private Issue parent;

    @Override
    public boolean hasChildrens() {
        return !childrens.isEmpty();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isNode() {
        return true;
    }
}
