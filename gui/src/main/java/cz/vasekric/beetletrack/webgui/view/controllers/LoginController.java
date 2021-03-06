package cz.vasekric.beetletrack.webgui.view.controllers;

import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.domain.service.UserService;
import cz.vasekric.beetletrack.webgui.view.mappers.UserMapper;
import cz.vasekric.beetletrack.webgui.view.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by vasek on 03.10.2015.
 */
@Named
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class LoginController implements Serializable {

    @Inject private User user;
    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/UserServiceEJB!cz.vasekric.beetletrack.domain.service.UserService")
    private UserService userService;
    @Inject private UserMapper userMapper;

    public boolean isLoggedIn(User user) {
        if(user.isAuthenticated()) {
            return true;
        }

        final UserDO userDO = UserDO.builder().username(user.getUsername()).password(user.getPassword()).build();
        if(!user.isAuthenticated()) {
            final UserDO processedUser = userService.authenticate(userDO);
            if(processedUser.isAuthenticated()) {
                user.setPassword("");
                user.setAuthenticated(processedUser.isAuthenticated());
                user.setId(processedUser.getId());
                return true;
            }
        }
        return false;
    }

    public void onLogin(User user) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/secured/welcome.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String register(User user) {
        final UserDO userDO = userMapper.map(user);
        final UserDO registeredUser = userService.register(userDO);
        final User mrUser = userMapper.map(userDO);
        this.isLoggedIn(mrUser);
        return "/secured/welcome.xhtml";
    }

    public void logout() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        try {
            context.redirect(context.getRequestContextPath() + "/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getLoggedUser() {
        return user;
    }
}
