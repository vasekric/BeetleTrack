package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.SpendTime;
import cz.vasekric.beetletrack.api.models.User;
import cz.vasekric.beetletrack.domain.models.SpendTimeDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 11.12.2015.
 */
@Component
public class UserMapper implements Mapper<UserDO, User> {

    @Override
    public User map(UserDO source) {
        if(source == null) {
            return null;
        }
        final User user = new User();
        user.id = source.getId();
        user.username = source.getUsername();
        user.fullName = source.getFullName();
        user.email = source.getEmail();

        return user;
    }

    public UserDO map(User source) {
        if(source == null) {
            return null;
        }
        return UserDO.builder()
                .id(source.id)
                .username(source.username)
                .fullName(source.fullName)
                .email(source.email)
                .build();
    }

    public List<UserDO> mapDO(List<User> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }
}
