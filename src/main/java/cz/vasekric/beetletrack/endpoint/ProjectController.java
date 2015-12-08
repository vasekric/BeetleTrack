package cz.vasekric.beetletrack.endpoint;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@RestController("api/projects")
@Component
public class ProjectController {

    @Inject private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET,
            produces = {"application/json"})
    public List<ProjectDO> getAllProjects() {
        return projectService.getAll();
    }
}
