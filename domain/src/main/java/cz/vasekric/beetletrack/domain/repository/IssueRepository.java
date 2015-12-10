package cz.vasekric.beetletrack.domain.repository;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.time.Duration;
import java.util.List;

/**
 * Created by vasek on 10.11.2015.
 */
@Remote
public interface IssueRepository {
    IssueDO create(IssueDO issue, Integer projectId);

    List<IssueDO> findAllByProjectId(Integer projectId);

    IssueDO createChild(IssueDO issueDO, Integer issueId);

    List<IssueDO> findAllByIssueId(Integer issueId);

    IssueDO findOne(Integer issueId);

    void addHours(Duration duration, Integer issueId);
}
