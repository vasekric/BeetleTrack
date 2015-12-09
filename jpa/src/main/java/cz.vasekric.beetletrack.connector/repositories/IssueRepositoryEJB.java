package cz.vasekric.beetletrack.connector.repositories;

import cz.vasekric.beetletrack.connector.mappers.IssueEntityMapper;
import cz.vasekric.beetletrack.connector.models.*;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.domain.repository.IssueRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Duration;
import java.util.List;

/**
 * Created by vasek on 05.12.2015.
 */
@Singleton
public class IssueRepositoryEJB implements IssueRepository {

    @PersistenceContext(name = "beetletrack-persistence")
    private EntityManager em;

    @Inject private IssueEntityMapper issueMapper;

    @Override
    public IssueDO create(IssueDO issue, Integer projectId) {
        final Issue mappedIssue = issueMapper.map(issue);
        final Project project = em.find(Project.class, projectId);
        mappedIssue.setProject(project);
        final Issue savedIssue = em.merge(mappedIssue);
        em.flush();
        return issueMapper.map(savedIssue);
    }

    @Override
    public List<IssueNodeDO> findAllByProjectId(Integer projectId) {
        final List<IssueNode> resultList = em.createQuery("SELECT i FROM IssueNode i WHERE i.project.id=:projectId AND i.parent=null", IssueNode.class)
                .setParameter("projectId", projectId)
                .getResultList();

        return issueMapper.mapNode(resultList);
    }

    @Override
    public IssueDO createChild(IssueDO issue, Integer issueId) {
        final Issue mappedIssue = issueMapper.map(issue);
        final IssueNode node = em.find(IssueNode.class, issueId);
        mappedIssue.setParent(node);
        mappedIssue.setProject(node.getProject());
        final Issue savedIssue = em.merge(mappedIssue);
        em.flush();
        return issueMapper.map(savedIssue);
    }

    @Override
    public List<IssueNodeDO> findAllByIssueId(Integer issueId) {
        final List<IssueNode> resultList = em.createQuery("SELECT i FROM IssueNode i WHERE i.id=:issueId", IssueNode.class)
                .setParameter("issueId", issueId)
                .getResultList();

        return issueMapper.mapNode(resultList);
    }

    @Override
    public IssueDO findOne(Integer issueId) {
        final Issue issue = em.find(Issue.class, issueId);
        return issueMapper.map(issue);
    }

    @Override
    public void addHours(Duration duration, Integer issueId) {
        final Issue issue = em.find(Issue.class, issueId);
        final SpendTime spendTime = SpendTime.builder()
                .issue(issue)
                .time(duration.toHours())
                .build();
        em.merge(spendTime);
        em.flush();
    }
}
