package cz.vasekric.beetletrack.connector.mappers;

import cz.vasekric.beetletrack.connector.models.Project;
import cz.vasekric.beetletrack.connector.models.User;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.models.UserDO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 04.12.2015.
 */
@Named
@ApplicationScoped
public class ProjectEntityMapper {

    @Inject private UserEntityMapper userMapper;

    public List<ProjectDO> map(List<Project> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public ProjectDO map(Project source) {
        if(source == null) {
            return null;
        }
        final List<UserDO> users = userMapper.map(source.getUsers());
        final UserDO projectManager = userMapper.map(source.getOwner());

        return ProjectDO.builder()
                .readme(source.getReadme())
                .id(source.getId())
                .name(source.getName())
                .users(users)
                .readme(source.getReadme())
                .projectManager(projectManager)
                //TODO: issues
                .build();
    }

    public Project map(ProjectDO source) {
        if(source == null) {
            return null;
        }
        final List<User> users = userMapper.mapDO(source.getUsers());
        final User projectManager = userMapper.map(source.getProjectManager());

        return Project.builder()
                .readme(source.getReadme())
                .id(source.getId())
                .name(source.getName())
                .users(users)
                .readme(source.getReadme())
                .owner(projectManager)
                //TODO: issues
                .build();
    }
}
