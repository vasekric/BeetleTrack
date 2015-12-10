package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.domain.models.*;
import org.springframework.jdbc.core.RowMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Created by vasek on 11.11.2015.
 */
@Named
@ApplicationScoped
public class IssueRowMapper implements RowMapper<IssueDO> {

    @Override
    public IssueDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String name = rs.getString("name");
        final int id = rs.getInt("id");
        final String description = rs.getString("description");
        final long estimatedTime = rs.getLong("estimatedTime");
        final String type = rs.getString("type");
        final int parentId = rs.getInt("parent_id");
        final int projectId = rs.getInt("project_id");
        final int userId = rs.getInt("userId");
        final String username = rs.getString("username");
        final String fullName = rs.getString("fullName");
        final String email = rs.getString("email");

        final UserDO user = UserDO.builder()
                .id(userId)
                .username(username)
                .fullName(fullName)
                .email(email)
                .build();


        if(rs.getString("DTYPE").equals("IssueNode")) {
            return IssueNodeDO.builder()
                    .id(id)
                    .name(name)
                    .description(description)
                    .assignedTo(user)
                    .parent(IssueNodeDO.builder().id(parentId).build())
                    .project(ProjectDO.builder().id(projectId).build())
                    .estimatedTime(Duration.ofHours(estimatedTime))
                    .type(IssueTypeDO.fromString(type))
                    .issues(new ArrayList<>())
                    .build();
        }
        else {
            return IssueLeafDO.builder()
                    .id(id)
                    .name(name)
                    .description(description)
                    .assignedTo(user)
                    .parent(IssueNodeDO.builder().id(parentId).build())
                    .project(ProjectDO.builder().id(projectId).build())
                    .estimatedTime(Duration.ofHours(estimatedTime))
                    .type(IssueTypeDO.fromString(type))
                    .build();
        }
    }
}
