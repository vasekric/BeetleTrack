package cz.vasekric.beetletrack.service.gateways;

import cz.vasekric.beetletrack.domain.models.UserDO;

/**
 * Created by vasek on 10.11.2015.
 */
public interface UserGateway {
    UserDO createUser(UserDO user);

    UserDO authenticate(UserDO user);
}
