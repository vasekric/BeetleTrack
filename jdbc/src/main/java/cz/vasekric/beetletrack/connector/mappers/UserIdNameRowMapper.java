package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.domain.models.UserDO;
import org.springframework.jdbc.core.RowMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vasek on 10.11.2015.
 */
@Named
@ApplicationScoped
public class UserIdNameRowMapper implements RowMapper<UserDO> {
    @Override
    public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final int id = rs.getInt("id");
        final String username = rs.getString("username");

        return UserDO.builder()
                     .id(id)
                     .username(username)
                     .build();
    }
}
