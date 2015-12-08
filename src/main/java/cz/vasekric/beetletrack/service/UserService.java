package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vasek on 10.11.2015.
 */
@Named
@ApplicationScoped
public class UserService {

    @EJB private UserRepository userRepository;

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
        return userRepository.findUser(userId);
    }

    public List<UserDO> getAllUsers() {
        return userRepository.findAllUsers();
    }
}
