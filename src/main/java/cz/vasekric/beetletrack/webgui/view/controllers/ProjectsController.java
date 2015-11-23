package cz.vasekric.beetletrack.webgui.view.controllers;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.service.ProjectService;
import cz.vasekric.beetletrack.webgui.view.mappers.ProjectMapper;
import cz.vasekric.beetletrack.webgui.view.models.Project;
import cz.vasekric.beetletrack.webgui.view.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Named
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class ProjectsController implements Serializable {

    @Inject private LoginController loginController;
    @Inject private ProjectService projectService;
    @Inject private ProjectMapper projectMapper;

    public List<Project> getProjects() {
        final List<ProjectDO> allProjects = projectService.getAll();
        return projectMapper.map(allProjects);
    }

    public Project getProject(Integer projectId) {

        final ProjectDO project = projectService.getProject(projectId);

        List<User> users = new ArrayList<>();
        users.add(User.builder().id(2).username("n").build());
        users.add(User.builder().id(3).username("e").build());
        users.add(User.builder().id(4).username("k").build());
        users.add(User.builder().id(5).username("d").build());
        users.add(User.builder().id(6).username("o").build());

        final Project mappedProject = projectMapper.map(project);
        mappedProject.setUsers(users);

        return mappedProject;
    }

    public String create(Project project) {
        final User loggedUser = loginController.getLoggedUser();
        project.setProjectManager(loggedUser);
        final ProjectDO projectDO = projectMapper.map(project);
        projectService.createProject(projectDO);
        return "projects";
    }
}
