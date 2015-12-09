package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.service.RemoteService;

import javax.ejb.Stateless;

/**
 * Created by vasek on 08.12.2015.
 */
@Stateless
public class RemoteServiceEJB implements RemoteService {
    @Override
    public String getHello() {
        return "hello";
    }
}
