package cz.vasekric.beetletrack.restapi.endpoints;

import cz.vasekric.beetletrack.api.models.User;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.service.UserService;
import cz.vasekric.beetletrack.restapi.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/UserServiceEJB!cz.vasekric.beetletrack.domain.service.UserService")
    private UserService userService;

    @Autowired private UserMapper userMapper;

    @RequestMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        final UserDO userById = userService.getUserById(id);
        return userMapper.map(userById);
    }

    @RequestMapping("/all")
    public List<User> getAllUser() {
        final List<UserDO> allUsers = userService.getAllUsers();
        return userMapper.map(allUsers);
    }

    @RequestMapping(value = "/authenticate",
                    method = RequestMethod.POST)
    public User authenticate(@RequestBody User user) {
        return userMapper.map(userService.authenticate(userMapper.map(user)));
    }

    @RequestMapping(value = "/register",
                    method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        final UserDO registered = userService.register(userMapper.map(user));
        return userMapper.map(registered);
    }
}
