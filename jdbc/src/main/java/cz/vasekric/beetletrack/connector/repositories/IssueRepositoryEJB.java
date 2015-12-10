package cz.vasekric.beetletrack.connector.repositories;

import cz.vasekric.beetletrack.connector.annotations.MySQLDependent;
import cz.vasekric.beetletrack.connector.mappers.IssueRowMapper;
import cz.vasekric.beetletrack.connector.mappers.SpentTimeRowMapper;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueLeafDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import cz.vasekric.beetletrack.domain.repository.IssueRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vasek on 11.11.2015.
 */
@Singleton
@MySQLDependent
public class IssueRepositoryEJB implements IssueRepository {

    @Inject private IssueRowMapper issueMapper;
    @Inject private SpentTimeRowMapper spentTimeMapper;
    @Inject private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcIssueInsert;
    private SimpleJdbcInsert jdbcSpendTimeInsert;

    @PostConstruct
    public void init() {
        jdbcIssueInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Issue")
                .usingColumns("DTYPE", "id", "description", "estimatedTime", "name", "type", "project_id", "parent_id", "assignedTo_id")
                .usingGeneratedKeyColumns("id");
        jdbcSpendTimeInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("SpendTime")
                .usingColumns("id", "user_id", "time", "issue_id")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public IssueDO create(IssueDO issue, Integer projectId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", issue.getName());
        params.put("project_id", projectId);
        params.put("parent_id", issue.getParent() != null ? issue.getParent().getId() : null);
        params.put("assignedTo_id", issue.getAssignedTo() != null ? issue.getAssignedTo().getId(): null);
        params.put("DTYPE", issue instanceof IssueNodeDO ? "IssueNode" : "IssueLeaf");
        params.put("description", issue.getDescription());
        params.put("estimatedTime", issue.getEstimatedTime() != null ? issue.getEstimatedTime().toHours() : null);
        params.put("type", null);

        final int id = jdbcIssueInsert.executeAndReturnKey(params).intValue();

        issue.setId(id);
        return issue;
    }

    @Override
    public IssueDO createChild(IssueDO issue, Integer parentId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", issue.getName());
        params.put("parent_id", parentId);
        params.put("project_id", issue.getProject() != null ? issue.getProject().getId() : null);
        params.put("assignedTo_id", issue.getAssignedTo() != null ? issue.getAssignedTo().getId(): null);
        params.put("DTYPE", issue instanceof IssueNodeDO ? "IssueNode" : "IssueLeaf");
        params.put("description", issue.getDescription());
        params.put("estimatedTime", issue.getEstimatedTime() != null ? issue.getEstimatedTime().toHours() : null);
        params.put("type", null);

        final int id = jdbcIssueInsert.executeAndReturnKey(params).intValue();

        issue.setId(id);
        return issue;
    }

    @Override
    public List<IssueDO> findAllByIssueId(Integer issueId) {
        final String sql = "SELECT *, u.id as userId FROM Issue i JOIN User u ON u.id=i.assignedTo_id where i.parent_id=?";

        final List<IssueDO> issues = jdbcTemplate.query(sql, new Object[]{issueId}, issueMapper);

        return issues;
    }

    @Override
    public IssueDO findOne(Integer issueId) {
        final String sql = "SELECT *, u.id as userId FROM Issue i JOIN User u ON u.id=i.assignedTo_id where i.id=?";

        final IssueDO issue = jdbcTemplate.queryForObject(sql, new Object[]{issueId}, issueMapper);

        if(issue instanceof IssueNodeDO) {
            final String childrenSql = "SELECT *, u.id as userId FROM Issue i JOIN User u ON u.id=i.assignedTo_id WHERE i.parent_id=?";
            final List<IssueDO> children = jdbcTemplate.query(childrenSql, new Object[]{issueId}, issueMapper);
            ((IssueNodeDO)issue).setIssues(children);
        }
        else {
            final List<SpendTimeDO> spentTime = findSpentTime(issueId);
            ((IssueLeafDO)issue).setSpentTime(spentTime);
        }

        return issue;
    }

    private List<SpendTimeDO> findSpentTime(Integer issueId) {
        final String sql = "SELECT * FROM SpendTime where issue_id=?";
        final List<SpendTimeDO> spentTime = jdbcTemplate.query(sql, new Object[]{issueId}, spentTimeMapper);
        return spentTime;
    }

    @Override
    public void addHours(Duration duration, Integer issueId) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("time", duration.toHours());
        params.put("issue_id", issueId);
        params.put("user_id", null);

        jdbcSpendTimeInsert.execute(params);
    }

    @Override
    public List<IssueDO> findAllByProjectId(Integer projectId) {
        final String sql = "SELECT *, u.id as userId FROM Issue i JOIN User u ON u.id=i.assignedTo_id WHERE i.project_id=? and i.parent_id IS NULL";

        final List<IssueDO> issues = jdbcTemplate.query(sql, new Object[]{projectId}, issueMapper);

        return issues;
    }
}
