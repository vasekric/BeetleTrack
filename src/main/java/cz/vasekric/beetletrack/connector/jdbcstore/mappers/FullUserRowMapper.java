package cz.vasekric.beetletrack.connector.jdbcstore.mappers;

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
public class FullUserRowMapper implements RowMapper<UserDO> {
    @Override
    public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final int id = rs.getInt("id");
        final String username = rs.getString("username");
        final String fullName = rs.getString("fullName");
        final String email = rs.getString("email");
        final String password = rs.getString("password");

        return UserDO.builder()
                     .id(id)
                     .username(username)
                     .fullName(fullName)
                     .email(email)
                     .password(password)
                     .build();
    }
}
