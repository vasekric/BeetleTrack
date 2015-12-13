package cz.vasekric.beetletrack.restapi.endpoints;

import cz.vasekric.beetletrack.api.models.Issue;
import cz.vasekric.beetletrack.api.models.IssueCommon;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.service.IssueService;
import cz.vasekric.beetletrack.restapi.mappers.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired private IssueMapper issueMapper;

    @RequestMapping("/{id}")
    public IssueCommon getIssue(@PathVariable Integer id) {
        final IssueDO issue = issueService.getIssue(id);
        return issueMapper.map(issue);
    }

    @RequestMapping(value = "/logwork/{issueId}",
                    method = RequestMethod.PUT)
    public boolean logWork(@PathVariable Integer issueId, @RequestParam Integer hours, @RequestParam boolean force) {
        return issueService.logWork(Duration.ofHours(hours), issueId, force);
    }

    @RequestMapping(value = "/children/{parentId}",
                    method = RequestMethod.POST)
    public IssueCommon createIssueChild(@RequestBody IssueCommon issue, @PathVariable Integer parentId) {
        final IssueDO child = issueService.createChild(issueMapper.map(issue), parentId);
        return issueMapper.map(child);
    }

    @RequestMapping("/children/{id}")
    public List<IssueCommon> getChildren(@PathVariable Integer id) {
        final List<IssueDO> children = issueService.getAllByIssueId(id);
        return issueMapper.map(children);
    }

    @RequestMapping(value = "/project/{projectId}",
                    method = RequestMethod.POST)
    public IssueCommon createIssue(@RequestBody IssueCommon issue, @PathVariable Integer projectId) {
        final IssueDO issueDO = issueService.create(issueMapper.map(issue), projectId);
        return issueMapper.map(issueDO);
    }

    @RequestMapping("/project/{projectId}")
    public List<IssueCommon> getIssuesByProject(@PathVariable Integer projectId) {
        final List<IssueDO> all = issueService.getAllByProjectId(projectId);
        return issueMapper.map(all);
    }
}
