package cz.vasekric.beetletrack.service.gateways;


import cz.vasekric.beetletrack.domain.models.ProjectDO;

import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */
public interface ProjectGateway {
    ProjectDO save(ProjectDO project);
    List<ProjectDO> findAll();
    ProjectDO findById(Integer id);
}
