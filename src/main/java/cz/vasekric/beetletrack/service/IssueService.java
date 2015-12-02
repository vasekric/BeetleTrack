package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.service.gateways.IssueGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vasek on 11.11.2015.
 */
@Named
@ApplicationScoped
public class IssueService {

    @Inject private IssueGateway issueRepository;

    public IssueDO create(IssueDO issue, Integer projectId) {
        return issueRepository.create(issue, projectId);
    }

    public List<IssueNodeDO> getAllByProjectId(Integer projectId) {
        return issueRepository.findAllByProjectId(projectId);
    }

    public IssueDO createChild(IssueDO issueDO, Integer issueId) {
        return issueRepository.createChild(issueDO, issueId);
    }

    public List<IssueNodeDO> getAllByIssueId(Integer issueId) {
        return issueRepository.findAllByIssueId(issueId);
    }

    public IssueDO getIssue(Integer issueId) {
        return null;
    }
}
