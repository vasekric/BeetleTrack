package cz.vasekric.beetletrack.webgui.view.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Created by vasek on 04.10.2015.
 */
@ManagedBean
@RequestScoped
public class NavigationController {

    @ManagedProperty(value="#{param.projectId}")
    private String projectId;
    public String showIssues() {
        return "secured/issues";
    }
}
