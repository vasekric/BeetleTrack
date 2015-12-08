package cz.vasekric.beetletrack.connector.jdbcstore.repositories;

import cz.vasekric.beetletrack.connector.jdbcstore.annotations.MySQLDependent;
import cz.vasekric.beetletrack.connector.jdbcstore.mappers.IssueNodeRowMapper;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.domain.repository.IssueRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vasek on 11.11.2015.
 */
//@Singleton
@MySQLDependent
public class IssueRepositoryImpl implements IssueRepository {

    @Inject private IssueNodeRowMapper issueNodeMapper;
    @Inject private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcNodeInsert;

    @PostConstruct
    public void init() {
        jdbcNodeInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("IssueNode")
                .usingColumns("id", "name", "project_id", "parent_id");
    }

    @Override
    public IssueDO create(IssueDO issue, Integer projectId) {
        final String sql = "SELECT next_val from hibernate_sequence limit 1";
        final String updateSql = "UPDATE hibernate_sequence SET next_val = ?";
        final int issueId = jdbcTemplate.queryForInt(sql);
        jdbcTemplate.update(updateSql, issueId+1);

        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", issueId);
        params.put("name", issue.getName());
        params.put("project_id", projectId);
        params.put("parent_id", null);

        jdbcNodeInsert.execute(params);

        issue.setId(issueId);
        return issue;
    }

    @Override
    public IssueDO createChild(IssueDO issue, Integer parentId) {
        final String sql = "SELECT next_val from hibernate_sequence limit 1";
        final String updateSql = "UPDATE hibernate_sequence SET next_val = ?";
        final int issueId = jdbcTemplate.queryForInt(sql);
        jdbcTemplate.update(updateSql, issueId+1);

        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", issueId);
        params.put("name", issue.getName());
        params.put("project_id", null);
        params.put("parent_id", parentId);

        jdbcNodeInsert.execute(params);

        issue.setId(issueId);
        return issue;
    }

    @Override
    public List<IssueNodeDO> findAllByIssueId(Integer issueId) {
        final String sql = "SELECT * FROM IssueNode where parent_id=?";

        final List<IssueNodeDO> issues = jdbcTemplate.query(sql, new Object[]{issueId}, issueNodeMapper);

        return issues;
    }

    @Override
    public IssueDO findOne(Integer issueId) {
        return null;
    }

    @Override
    public void addHours(Duration duration, Integer issueId) {

    }

    @Override
    public List<IssueNodeDO> findAllByProjectId(Integer projectId) {
        final String sql = "SELECT * FROM IssueNode where project_id=?";

        final List<IssueNodeDO> issues = jdbcTemplate.query(sql, new Object[]{projectId}, issueNodeMapper);

        return issues;
    }
}
