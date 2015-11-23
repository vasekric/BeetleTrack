package cz.vasekric.beetletrack.service.connectors;


import cz.vasekric.beetletrack.domain.models.ProjectDO;

import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */
public interface ProjectRepository {
    ProjectDO save(ProjectDO project);
    List<ProjectDO> findAll();
    ProjectDO findById(Integer id);
}
