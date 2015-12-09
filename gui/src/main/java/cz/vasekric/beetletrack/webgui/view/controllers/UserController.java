package cz.vasekric.beetletrack.webgui.view.controllers;

import cz.vasekric.beetletrack.domain.service.UserService;
import cz.vasekric.beetletrack.webgui.view.mappers.UserMapper;
import cz.vasekric.beetletrack.webgui.view.models.User;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by vasek on 07.12.2015.
 */
@Named
@ApplicationScoped
public class UserController {
    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/UserServiceEJB!cz.vasekric.beetletrack.domain.service.UserService")
    private UserService userService;
    @Inject private UserMapper userMapper;

    public User getUser(Integer id) {
        return userMapper.map(userService.getUserById(id));
    }

    public List<User> getAllUsers() {
        return userMapper.map(userService.getAllUsers());
    }


}
