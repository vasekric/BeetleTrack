package cz.vasekric.beetletrack.service.gateways;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;

import java.util.List;

/**
 * Created by vasek on 10.11.2015.
 */
public interface IssueGateway {
    IssueDO create(IssueDO issue, Integer projectId);

    List<IssueNodeDO> findAllByProjectId(Integer projectId);

    IssueDO createChild(IssueDO issueDO, Integer issueId);

    List<IssueNodeDO> findAllByIssueId(Integer issueId);
}
