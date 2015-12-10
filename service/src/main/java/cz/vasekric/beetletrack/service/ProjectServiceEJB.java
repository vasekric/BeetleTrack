package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.repository.ProjectRepository;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vasek on 18.10.2015.
 */

@Stateless
public class ProjectServiceEJB implements ProjectService, Serializable {

//    @EJB(mappedName = "java:jboss/exported/beetletrack.jpa-exploded/ProjectRepositoryEJB!cz.vasekric.beetletrack.domain.repository.ProjectRepository")
    @EJB(mappedName = "java:jboss/exported/beetletrack.jdbc-exploded/ProjectRepositoryEJB!cz.vasekric.beetletrack.domain.repository.ProjectRepository")
    private ProjectRepository projectRepository;

    @Override
    public ProjectDO createProject(ProjectDO project) {
        return projectRepository.save(project);
    }

    @Override
    public List<ProjectDO> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectDO getProject(Integer projectId) {
        if(projectId == null) {
            return new ProjectDO();
        }
        return projectRepository.findById(projectId);
    }
}
