package cz.vasekric.beetletrack.domain.service;

import cz.vasekric.beetletrack.domain.models.ProjectDO;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

/**
 * Created by vasek on 08.12.2015.
 */
@Remote
public interface ProjectService {

    ProjectDO createProject(ProjectDO project);
    List<ProjectDO> getAll();
    ProjectDO getProject(Integer projectId);
}
