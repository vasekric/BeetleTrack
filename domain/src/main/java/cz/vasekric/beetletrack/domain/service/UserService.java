package cz.vasekric.beetletrack.domain.service;

import cz.vasekric.beetletrack.domain.models.UserDO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@Remote
public interface UserService {

    UserDO register(UserDO user);
    UserDO authenticate(UserDO user);
    UserDO getUserById(Integer userId);
    List<UserDO> getAllUsers();
}