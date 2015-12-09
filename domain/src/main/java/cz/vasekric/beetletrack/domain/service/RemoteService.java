package cz.vasekric.beetletrack.domain.service;

import javax.ejb.Remote;

/**
 * Created by vasek on 08.12.2015.
 */
@Remote
public interface RemoteService {
    String getHello();
}
