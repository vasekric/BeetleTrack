package cz.vasekric.beetletrack.domain.repository;


import cz.vasekric.beetletrack.domain.models.ProjectDO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */
@Remote
public interface ProjectRepository {
    ProjectDO save(ProjectDO project);
    List findAll();
    ProjectDO findById(Integer id);
}
