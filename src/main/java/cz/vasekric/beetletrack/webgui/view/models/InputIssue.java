package cz.vasekric.beetletrack.webgui.view.models;

import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;

/**
 * Created by vasek on 18.11.2015.
 */
@ManagedBean
@Getter
@Setter
public class InputIssue {

    private String name;
    private String description;
    private String tags;
    private String type;
    private Integer assignedTo;
    private String estimatedTime;
    private Integer parent;
    private Integer project;
}
