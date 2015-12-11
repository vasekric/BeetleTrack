package cz.vasekric.beetletrack.restapi.endpoints;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.service.ProjectService;
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

    @RequestMapping("/{id}")
    public ProjectDO getProject(@PathVariable Integer id) {
        return projectService.getProject(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProjectDO createProject(@RequestBody ProjectDO project) {
        return projectService.createProject(project);
    }

    @RequestMapping("/all")
    public List<ProjectDO> getAllProjects() {
        return projectService.getAll();
    }
}
