package cz.vasekric.beetletrack.connector.jdbcstore.mappers;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import org.springframework.jdbc.core.RowMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vasek on 04.11.2015.
 */
@Named
@ApplicationScoped
public class ProjectRowMapper implements RowMapper<ProjectDO>{

    @Override
    public ProjectDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final int userId = rs.getInt("user_id");
        final String username = rs.getString("username");
        final String fullName = rs.getString("full_name");
        final String email = rs.getString("email");
        final Integer projectId = rs.getInt("project_id");
        final String name = rs.getString("project_name");

        final UserDO owner = UserDO.builder()
                .id(userId)
                .username(username)
                .fullName(fullName)
                .email(email)
                .build();

        return ProjectDO.builder()
                .id(projectId)
                .name(name)
                .projectManager(owner)
                .build();
    }
}
