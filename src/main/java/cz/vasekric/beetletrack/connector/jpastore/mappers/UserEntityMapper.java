package cz.vasekric.beetletrack.connector.jpastore.mappers;

import cz.vasekric.beetletrack.connector.jpastore.models.User;
import cz.vasekric.beetletrack.domain.models.UserDO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by vasek on 04.12.2015.
 */
@Named
@ApplicationScoped
public class UserEntityMapper {

    public List<UserDO> map(List<User> source) {
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public List<User> mapDO(List<UserDO> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public UserDO map(User source) {
        if(source == null) {
            return null;
        }
        return UserDO.builder()
                .email(source.getEmail())
                .fullName(source.getFullName())
                .username(source.getUsername())
                .id(source.getId())
                .password(source.getPassword())
                .build();
    }

    public User map(UserDO source) {
        if(source == null) {
            return null;
        }
        return User.builder()
                .email(source.getEmail())
                .fullName(source.getFullName())
                .username(source.getUsername())
                .id(source.getId())
                .password(source.getPassword())
                .build();
    }
}
