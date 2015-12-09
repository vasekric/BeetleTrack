package cz.vasekric.beetletrack.webgui.view.models;

import javax.faces.bean.ManagedBean;

/**
 * Created by vasek on 18.11.2015.
 */
@ManagedBean
public class InputIssue {

    private String name;
    private String description;
    private String tags;
    private String type;
    private Integer assignedTo;
    private String estimatedTime;
    private Integer parent;
    private Integer project;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTags() {
        return this.tags;
    }

    public String getType() {
        return this.type;
    }

    public Integer getAssignedTo() {
        return this.assignedTo;
    }

    public String getEstimatedTime() {
        return this.estimatedTime;
    }

    public Integer getParent() {
        return this.parent;
    }

    public Integer getProject() {
        return this.project;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public void setProject(Integer project) {
        this.project = project;
    }
}
