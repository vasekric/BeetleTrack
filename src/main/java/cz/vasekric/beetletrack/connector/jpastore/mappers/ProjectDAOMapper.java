package cz.vasekric.beetletrack.connector.jpastore.mappers;

import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.connector.jpastore.models.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Created by vasek on 04.11.2015.
 */
@Named
@ApplicationScoped
public class ProjectDAOMapper {
    public Project map(ProjectDO source) {
        final Integer id = source.getId();
        final String name = source.getName();

        final Project project = new Project();
        project.setName(name);

        return project;
    }
}
