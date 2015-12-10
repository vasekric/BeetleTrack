package cz.vasekric.beetletrack.domain.service;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;

import javax.ejb.Remote;
import java.time.Duration;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@Remote
public interface IssueService {

     IssueDO create(IssueDO issue, Integer projectId);
     List<IssueDO> getAllByProjectId(Integer projectId);
     IssueDO createChild(IssueDO issueDO, Integer issueId);
     List<IssueDO> getAllByIssueId(Integer issueId);
     IssueDO getIssue(Integer issueId);
    boolean logWork(Duration duration, Integer issueId, boolean force);
}
