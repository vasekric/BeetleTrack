package endpoints;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import cz.vasekric.beetletrack.domain.service.UserService;
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

    @RequestMapping("/{id}")
    public UserDO getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/all")
    public List<UserDO> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/authenticate",
                    method = RequestMethod.POST)
    public UserDO authenticate(@RequestBody UserDO user) {
        return userService.authenticate(user);
    }

    @RequestMapping(value = "/register",
                    method = RequestMethod.POST)
    public UserDO register(@RequestBody UserDO user) {
        return userService.register(user);
    }
}
