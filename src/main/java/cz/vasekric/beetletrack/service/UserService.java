package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.service.gateways.UserGateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vasek on 10.11.2015.
 */
@Named
@ApplicationScoped
public class UserService {

    @Inject private UserGateway userRepository;

    public UserDO register(UserDO user) {
        return userRepository.createUser(user);
    }

    public UserDO authenticate(UserDO user) {
        if(user == null || user.getUsername() == null || user.getPassword() == null) {
            return user;
        }
        final UserDO authenticated = userRepository.authenticate(user);
        authenticated.setPassword("");
        return authenticated;
    }

    public UserDO getUserById(Integer userId) {
        return null;
    }
}
