package cz.vasekric.beetletrack.webgui.view.mappers;

import cz.vasekric.beetletrack.domain.models.*;
import cz.vasekric.beetletrack.service.IssueService;
import cz.vasekric.beetletrack.service.ProjectService;
import cz.vasekric.beetletrack.service.UserService;
import cz.vasekric.beetletrack.webgui.view.models.InputIssue;
import cz.vasekric.beetletrack.webgui.view.models.Issue;
import cz.vasekric.beetletrack.webgui.view.models.IssueLeaf;
import cz.vasekric.beetletrack.webgui.view.models.IssueNode;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 18.10.2015.
 */
@Named
@ApplicationScoped
public class IssueMapper implements Serializable {

    @Inject private UserService userService;
    @Inject private ProjectService projectService;
    @Inject private IssueService issueService;

    public List<IssueDO> map(List<Issue> source) {
        if(source == null) {
            return null;
        }
        return source.stream()
                     .map(this::map)
                     .collect(Collectors.toList());
    }

    public List<Issue> mapDO(List<IssueDO> source) {
        if(source == null) {
            return null;
        }
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public IssueDO map(Issue source) {
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
        final Issue issue;
        if(source instanceof IssueNodeDO) {
            issue = IssueNode.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .build();
        }
        else {
            issue = IssueLeaf.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .build();
        }
        //TODO: implements
        return issue;
    }

    public IssueDO map(InputIssue source) {
        final List<String> tags = Arrays.asList(source.getTags().split(","));
        final Integer userId = source.getAssignedTo();
        final UserDO user = userService.getUserById(userId);
        final String estimatedTimeStr = source.getEstimatedTime();
        //TODO: map estimatedTime
        Period estimatedTime = Period.ZERO;
        final Integer projectId = source.getParent();
        final Integer issueId = source.getProject();
        final ProjectDO project = projectService.getProject(projectId);
        final IssueDO parent = issueService.getIssue(issueId);

        final IssueTypeDO issueType = IssueTypeDO.fromString(source.getType());
        if (issueType.equals(IssueTypeDO.EPIC) || issueType.equals(IssueTypeDO.USER_STORY)) {
            return IssueNodeDO.builder()
                        .name(source.getName())
                        .description(source.getDescription())
                        .assignedTo(user)
                        .estimatedTime(estimatedTime)
                        .parent(parent)
                        .project(project)
                        .type(issueType)
                        .build();
        } else {
            return IssueLeafDO.builder()
                        .name(source.getName())
                        .description(source.getDescription())
                        .assignedTo(user)
                        .estimatedTime(estimatedTime)
                        .parent(parent)
                        .project(project)
                        .type(issueType)
                        .build();
        }
    }

}
