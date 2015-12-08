package cz.vasekric.beetletrack.webgui.view.mappers;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.ProjectDO;
import cz.vasekric.beetletrack.domain.models.UserDO;
import cz.vasekric.beetletrack.webgui.view.models.Issue;
import cz.vasekric.beetletrack.webgui.view.models.Project;
import cz.vasekric.beetletrack.webgui.view.models.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 18.10.2015.
 */
@Named
@ApplicationScoped
public class ProjectMapper implements Serializable {

    @Inject private UserMapper userMapper;
    @Inject private IssueMapper issueMapper;

    public List<Project> map(List<ProjectDO> source) {
        return source.stream()
                     .map(this::map)
                     .collect(Collectors.toList());
    }


    public ProjectDO map(Project source) {
        final Integer id = source.getId();
        final String name = source.getName();
        final User owner = source.getProjectManager();
        final List<Issue> issues = source.getIssues();
        final UserDO ownerDO = userMapper.map(owner);
        final List<UserDO> users = userMapper.mapDO(source.getUsers());
        final List<IssueDO> issueDOs = issueMapper.map(issues);

        return ProjectDO.builder()
                        .id(id)
                        .name(name)
                        .projectManager(ownerDO)
                        .issues(issueDOs)
                        .users(users)
                        .readme(source.getReadme())
                        .build();
    }


    public Project map(ProjectDO source) {
        final Integer id = source.getId();
        final String name = source.getName();
        final UserDO ownerDO = source.getProjectManager();
        final List<IssueDO> issuesDO = source.getIssues();
        final User owner = userMapper.map(ownerDO);
        final List<User> users = userMapper.map(source.getUsers());
        final List<Issue> issues = issueMapper.mapDO(issuesDO);

        return Project.builder()
                        .id(id)
                        .name(name)
                        .projectManager(owner)
                        .users(users)
                        .issues(issues)
                        .readme(source.getReadme())
                        .build();
    }
}
