package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.Project;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vasek on 12.12.2015.
 */
@Component
public class ProjectMapper implements Mapper<ProjectDO, Project> {

    @Autowired private UserMapper userMapper;
    @Autowired private IssueMapper issueMapper;

    @Override
    public Project map(ProjectDO source) {
        if(source == null) {
            return null;
        }
        final Project project = new Project();
        project.id = source.getId();
        project.name = source.getName();
        project.readme = source.getReadme();
        project.issues = issueMapper.map(source.getIssues());
        project.projectManager = userMapper.map(source.getProjectManager());
        project.users = userMapper.map(source.getUsers());

        return project;
    }

    public ProjectDO map(Project source) {
        if(source == null) {
            return null;
        }
        return ProjectDO.builder()
                .users(userMapper.mapDO(source.users))
                .projectManager(userMapper.map(source.projectManager))
                .name(source.name)
                .id(source.id)
                .readme(source.readme)
                .issues(issueMapper.mapCom(source.issues))
                .build();
    }
}
