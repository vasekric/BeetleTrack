package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.repository.ProjectRepository;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */

@Named
@ApplicationScoped
@Getter
@Setter
public class ProjectService implements Serializable {

    @EJB private ProjectRepository projectRepository;

    public ProjectDO createProject(ProjectDO project) {
        return projectRepository.save(project);
    }

    public List<ProjectDO> getAll() {
        return projectRepository.findAll();
    }

    public ProjectDO getProject(Integer projectId) {
        if(projectId == null) {
            return new ProjectDO();
        }
        return projectRepository.findById(projectId);
    }
}
