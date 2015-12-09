package cz.vasekric.beetletrack.domain.repository;

import cz.vasekric.beetletrack.domain.models.UserDO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

/**
 * Created by vasek on 10.11.2015.
 */
@Remote
public interface UserRepository {
    UserDO createUser(UserDO user);

    UserDO authenticate(UserDO user);

    List<UserDO> findAllUsers();

    UserDO findUser(Integer id);
}
