package endpoints;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import cz.vasekric.beetletrack.domain.service.RemoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@RestController
public class ProjectEndpoint {

    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/ProjectServiceEJB!cz.vasekric.beetletrack.domain.service.ProjectService")
    private ProjectService projectService;

        @RequestMapping("/all")
        public List<ProjectDO> getAllProjects() {
            return projectService.getAll();
        }
}
