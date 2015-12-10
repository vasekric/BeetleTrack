package endpoints;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.service.IssueService;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;
import java.time.Duration;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@RestController
@RequestMapping("api/issues")
public class IssueController {

    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/IssueServiceEJB!cz.vasekric.beetletrack.domain.service.IssueService")
    private IssueService issueService;

    @RequestMapping("/{id}")
    public IssueDO getIssue(@PathVariable Integer id) {
        return issueService.getIssue(id);
    }

    @RequestMapping(value = "/logwork/{issueId}",
                    method = RequestMethod.PUT)
    public boolean logWork(@PathVariable Integer issueId, @RequestParam Integer hours, @RequestParam boolean force) {
        return issueService.logWork(Duration.ofHours(hours), issueId, force);
    }

    @RequestMapping(value = "/children/{parentId}",
                    method = RequestMethod.POST)
    public IssueDO createIssueChild(@RequestBody IssueDO issue, @PathVariable Integer parentId) {
        return issueService.createChild(issue, parentId);
    }

    @RequestMapping("/children/{id}")
    public List<IssueDO> getChildren(@PathVariable Integer id) {
        return issueService.getAllByIssueId(id);
    }

    @RequestMapping(value = "/project/{projectId}",
                    method = RequestMethod.POST)
    public IssueDO createIssue(@RequestBody IssueDO issue, @PathVariable Integer projectId) {
        return issueService.create(issue, projectId);
    }

    @RequestMapping("/project/{projectId}")
    public List<IssueDO> getIssuesByProject(@PathVariable Integer projectId) {
        return issueService.getAllByProjectId(projectId);
    }
}
