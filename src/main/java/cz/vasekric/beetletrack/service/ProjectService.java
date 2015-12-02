package cz.vasekric.beetletrack.service;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.service.gateways.ProjectGateway;
import lombok.Getter;
import lombok.Setter;

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

    @Inject private ProjectGateway projectRepository;

    public ProjectDO createProject(ProjectDO project) {
        return projectRepository.save(project);
    }

    public List<ProjectDO> getAll() {
        return projectRepository.findAll();
    }

    public ProjectDO getProject(Integer projectId) {
        return projectRepository.findById(projectId);
    }
}
