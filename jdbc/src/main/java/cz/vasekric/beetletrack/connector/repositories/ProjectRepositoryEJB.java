package cz.vasekric.beetletrack.connector.repositories;

import cz.vasekric.beetletrack.connector.annotations.MySQLDependent;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.connector.mappers.ProjectRowMapper;
import cz.vasekric.beetletrack.domain.repository.ProjectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */
//@Singleton
@MySQLDependent
public class ProjectRepositoryEJB implements ProjectRepository, Serializable {

    @Inject private ProjectRowMapper projectMapper;
    @Inject private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    public void init() {
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("Project")
            .usingColumns("name", "owner_id", "readme");
        jdbcInsert.setGeneratedKeyName("id");
    }

    @Override
    public ProjectDO save(ProjectDO project) {
        final String sql = "INSERT INTO User_Project SET users_id=?, project_id=?";

        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", project.getName());
        params.put("owner_id", project.getProjectManager().getId());
        params.put("readme", project.getReadme());

        final int id = jdbcInsert.executeAndReturnKey(params).intValue();
        project.getUsers()
                .stream()
                .forEach(user -> jdbcTemplate.update(sql, user.getId(), project.getId()));

        project.setId(id);
        return project;
    }

    @Override
    public List<ProjectDO> findAll() {
        final String sql = "SELECT p.id as project_id, p.name as project_name, u.id as user_id, username, u.fullName as full_name, u.email as email, readme " +
                "FROM Project p JOIN User u on owner_id = u.id";

        final List<ProjectDO> projects = jdbcTemplate.query(sql, projectMapper);

        return projects;
    }

    @Override
    public ProjectDO findById(Integer id) {
        final String sql = "SELECT p.id as project_id, p.name as project_name, u.id as user_id, username, u.fullName as full_name, u.email as email, readme " +
                "FROM Project p JOIN User u on owner_id = u.id WHERE p.id=?";

        final ProjectDO project = jdbcTemplate.queryForObject(sql, new Object[]{id}, projectMapper);

        return project;
    }
}
