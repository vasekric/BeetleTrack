package cz.vasekric.beetletrack.restapi.endpoints;

import cz.vasekric.beetletrack.api.models.Project;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import cz.vasekric.beetletrack.restapi.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/ProjectServiceEJB!cz.vasekric.beetletrack.domain.service.ProjectService")
    private ProjectService projectService;

    @Autowired private ProjectMapper projectMapper;

    @RequestMapping("/{id}")
    public Project getProject(@PathVariable Integer id) {
        final ProjectDO project = projectService.getProject(id);
        return projectMapper.map(project);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Project createProject(@RequestBody Project project) {
        final ProjectDO createdProject = projectService.createProject(projectMapper.map(project));
        return projectMapper.map(createdProject);
    }

    @RequestMapping("/all")
    public List<Project> getAllProjects() {
        final List<ProjectDO> all = projectService.getAll();
        return projectMapper.map(all);
    }
}
