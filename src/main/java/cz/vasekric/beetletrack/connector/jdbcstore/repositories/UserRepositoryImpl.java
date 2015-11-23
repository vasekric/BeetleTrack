package cz.vasekric.beetletrack.connector.jdbcstore.repositories;

import cz.vasekric.beetletrack.connector.jdbcstore.annotations.MySQLDependent;
import cz.vasekric.beetletrack.connector.jdbcstore.mappers.FullUserRowMapper;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.service.connectors.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;

/**
 * Created by vasek on 10.11.2015.
 */
@Named
@ApplicationScoped
@MySQLDependent
public class UserRepositoryImpl implements UserRepository {

    @Inject private FullUserRowMapper userMapper;
    @Inject private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @PostConstruct
    public void init() {
        jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("User");
        jdbcInsert.usingColumns("username", "password", "fullName", "email");
        jdbcInsert.setGeneratedKeyName("id");
    }

    @Override
    public UserDO createUser(UserDO user) {
        final String hashpw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        final HashMap<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("fullName", user.getFullName());
        params.put("email", user.getEmail());
        params.put("password", hashpw);
        final int userId = jdbcInsert.executeAndReturnKey(params).intValue();

        user.setId(userId);
        user.setAuthenticated(true);

        return user;
    }

    @Override
    public UserDO authenticate(UserDO user) {
        final String sql = "SELECT * from User where username=?";
        try {
            final UserDO selectedUser = jdbcTemplate.queryForObject(sql, new Object[]{user.getUsername()}, userMapper);
            final boolean authenticated = BCrypt.checkpw(user.getPassword(), selectedUser.getPassword());

            user.setAuthenticated(authenticated);
            user.setId(selectedUser.getId());
        } catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }



}
