package cz.vasekric.beetletrack.service.connectors;

import cz.vasekric.beetletrack.domain.models.UserDO;

/**
 * Created by vasek on 10.11.2015.
 */
public interface UserRepository {
    UserDO createUser(UserDO user);

    UserDO authenticate(UserDO user);
}
