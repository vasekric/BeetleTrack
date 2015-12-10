package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.domain.repository.IssueRepository;
import cz.vasekric.beetletrack.domain.service.IssueService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 * Created by vasek on 11.11.2015.
 */
@Stateless
public class IssueServiceEJB implements IssueService, Serializable {

//    @EJB(mappedName = "java:jboss/exported/beetletrack.jpa-exploded/IssueRepositoryEJB!cz.vasekric.beetletrack.domain.repository.IssueRepository")
    @EJB(mappedName = "java:jboss/exported/beetletrack.jdbc-exploded/IssueRepositoryEJB!cz.vasekric.beetletrack.domain.repository.IssueRepository")
    private IssueRepository issueRepository;

    public IssueDO create(IssueDO issue, Integer projectId) {
        return issueRepository.create(issue, projectId);
    }

    public List<IssueDO> getAllByProjectId(Integer projectId) {
        return issueRepository.findAllByProjectId(projectId);
    }

    public IssueDO createChild(IssueDO issueDO, Integer issueId) {
        return issueRepository.createChild(issueDO, issueId);
    }

    public List<IssueDO> getAllByIssueId(Integer issueId) {
        return issueRepository.findAllByIssueId(issueId);
    }

    public IssueDO getIssue(Integer issueId) {
        return issueRepository.findOne(issueId);
    }

    public boolean logWork(Duration duration, Integer issueId, boolean force) {
        if(force) {
            issueRepository.addHours(duration, issueId);
            return true;
        }
        else {
            final IssueDO issue = issueRepository.findOne(issueId);
            final Duration estimatedTime = issue.getEstimatedTime() == null ? Duration.ZERO : issue.getEstimatedTime();
            final Duration timeLeft = issue.getTotalSpentTime().plus(duration).minus(estimatedTime);
            if(timeLeft.isNegative()) {
                return false;
            }
            else {
                return this.logWork(duration, issueId, true);
            }
        }
    }
}
