package cz.vasekric.beetletrack.webgui.view.mappers;

import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.webgui.view.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vasek on 18.10.2015.
 */
@Named
@ApplicationScoped
public class UserMapper implements Serializable {
    public UserDO map(User source) {
        if(source == null) {
            return null;
        }
        final Integer id = source.getId();
        final String username = source.getUsername();
        final String password = source.getPassword();
        final String fullName = source.getFullName();
        final String email = source.getEmail();
        final boolean authenticated = source.isAuthenticated();

        return UserDO.builder()
                     .id(id)
                     .username(username)
                     .fullName(fullName)
                     .email(email)
                     .password(password)
                     .authenticated(authenticated)
                     .build();
    }

    public User map(UserDO source) {
        if(source == null) {
            return null;
        }
        final Integer id = source.getId();
        final String username = source.getUsername();
        final String fullName = source.getFullName();
        final String email = source.getEmail();
        final String password = source.getPassword();
        final boolean authenticated = source.isAuthenticated();

        return User.builder()
                .id(id)
                .username(username)
                .email(email)
                .fullName(fullName)
                .password(password)
                .authenticated(authenticated)
                .build();
    }
}
