package cz.vasekric.beetletrack.webgui.view.mappers;

import cz.vasekric.beetletrack.domain.models.*;
import cz.vasekric.beetletrack.domain.service.IssueService;
import cz.vasekric.beetletrack.domain.service.ProjectService;
import cz.vasekric.beetletrack.domain.service.UserService;
import cz.vasekric.beetletrack.webgui.view.models.*;


import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 18.10.2015.
 */
@Named
@ApplicationScoped
public class IssueMapper implements Serializable {

    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/UserServiceEJB!cz.vasekric.beetletrack.domain.service.UserService")
    private UserService userService;
    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/ProjectServiceEJB!cz.vasekric.beetletrack.domain.service.ProjectService")
    private ProjectService projectService;
    @EJB(mappedName = "java:jboss/exported/beetletrack.service-exploded/IssueServiceEJB!cz.vasekric.beetletrack.domain.service.IssueService")
    private IssueService issueService;
    @Inject private UserMapper userMapper;
    @Inject private SpendTimeMapper spendTimeMapper;

    public List<IssueDO> map(List<Issue> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream()
                     .map(this::map)
                     .collect(Collectors.toList());
    }

    public List<Issue> mapDO(List<IssueDO> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public IssueDO map(Issue source) {
        if(source == null) {
            return null;
        }
        final IssueDO issue;
        if(source instanceof IssueNode) {
            issue = IssueNodeDO.builder()
                .name(source.getName())
            .build();
        }
        else {
            issue = IssueLeafDO.builder()
                .name(source.getName())
                .build();
        }
        //TODO: implements
        return issue;
    }
    public Issue map(IssueDO source) {
        if(source == null) {
            return null;
        }
        final Issue parent = this.map(source.getParent());
        final User assignedTo = userMapper.map(source.getAssignedTo());
        final Duration estimatedTime = source.getEstimatedTime();
        final Issue issue;
        if(source instanceof IssueNodeDO) {
            final List<IssueDO> issues = ((IssueNodeDO) source).getIssues();
            final List<Issue> children = this.mapDO(issues);
            issue = IssueNode.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .parent(parent)
                    .childrens(children)
                    .estimatedTime(estimatedTime)
                    .assignedTo(assignedTo)
                    .build();
        }
        else {
            final List<SpendTimeDO> spentTime = ((IssueLeafDO) source).getSpentTime();
            final List<SpendTime> spendTimeList = spendTimeMapper.mapDO(spentTime);
            issue = IssueLeaf.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .description(source.getDescription())
                    .parent(parent)
                    .estimatedTime(estimatedTime)
                    .spentTime(spendTimeList)
                    .assignedTo(assignedTo)
                    .build();
        }
        //TODO: implements
        return issue;
    }

    public IssueDO map(InputIssue source) {
        if(source == null) {
            return null;
        }
        final List<String> tags = Arrays.asList(source.getTags().split(","));
        final Integer userId = source.getAssignedTo();
        final UserDO user = userService.getUserById(userId);
        final Integer projectId = source.getParent();
        final Integer issueId = source.getProject();
        ProjectDO project = null;
        if(projectId != null) {
            project = projectService.getProject(projectId);
        }
        IssueDO parent = null;
        if(issueId != null) {
            parent = issueService.getIssue(issueId);
        }


        final IssueTypeDO issueType = IssueTypeDO.fromString(source.getType());
        if (issueType.equals(IssueTypeDO.EPIC) || issueType.equals(IssueTypeDO.USER_STORY)) {
            return IssueNodeDO.builder()
                        .name(source.getName())
                        .description(source.getDescription())
                        .assignedTo(user)
                        .parent((IssueNodeDO) parent)
                        .project(project)
                        .estimatedTime(Duration.parse("PT"+source.getEstimatedTime().toUpperCase()))
                        .type(issueType)
                        .build();
        } else {
            return IssueLeafDO.builder()
                        .name(source.getName())
                        .description(source.getDescription())
                        .assignedTo(user)
                        .parent((IssueNodeDO) parent)
                        .project(project)
                        .estimatedTime(Duration.parse("PT"+source.getEstimatedTime().toUpperCase()))
                        .type(issueType)
                        .build();
        }
    }

}
