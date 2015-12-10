package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.repository.UserRepository;
import cz.vasekric.beetletrack.domain.service.UserService;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vasek on 10.11.2015.
 */
@Stateless
public class UserServiceEJB implements UserService, Serializable {

//    @EJB(mappedName = "java:jboss/exported/beetletrack.jpa-exploded/UserRepositoryEJB!cz.vasekric.beetletrack.domain.repository.UserRepository")
    @EJB(mappedName = "java:jboss/exported/beetletrack.jdbc-exploded/UserRepositoryEJB!cz.vasekric.beetletrack.domain.repository.UserRepository")
    private UserRepository userRepository;

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
