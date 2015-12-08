package cz.vasekric.beetletrack.connector.jpastore.mappers;

import cz.vasekric.beetletrack.connector.jpastore.models.*;
import cz.vasekric.beetletrack.domain.models.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 05.12.2015.
 */
@Named
@ApplicationScoped
public class IssueEntityMapper {

    @Inject private UserEntityMapper userMapper;
    @Inject private ProjectEntityMapper projectMapper;
    @Inject private SpentTimeEntityMapper spentTimeMapper;

    public List<IssueNodeDO> mapNode(List<IssueNode> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public List<IssueDO> map(List<Issue> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public List<Issue> mapDO(List<IssueDO> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }

    public IssueDO map(Issue source) {
        if(source == null) {
            return null;
        }
        if(source instanceof IssueNode) {
            return map((IssueNode) source);
        }
        else {
            return map((IssueLeaf) source);
        }
    }

    public Issue map(IssueDO source) {
        if(source == null) {
            return null;
        }
        if(source instanceof IssueNodeDO) {
            return map((IssueNodeDO) source);
        }
        else {
            return map((IssueLeafDO) source);
        }
    }

    public IssueLeaf map(IssueLeafDO source) {
        if(source == null) {
            return null;
        }
        final User user = userMapper.map(source.getAssignedTo());
        final Project project = projectMapper.map(source.getProject());
        if(source.getParent() != null) {
            ((IssueNodeDO) source.getParent()).setIssues(null);
        }
        final List<SpendTime> spentTime = spentTimeMapper.mapDO(source.getSpentTime());
        final Issue parent = this.map(source.getParent());
        final IssueLeaf target = IssueLeaf.builder()
                .description(source.getDescription())
                .name(source.getName())
                .assignedTo(user)
                .type(source.getType().getText())
                .spentTime(spentTime)
                .estimatedTime(source.getEstimatedTime() == null ? 0 : source.getEstimatedTime().toHours())
                .build();

        target.setId(source.getId());
        target.setParent(parent);
        target.setProject(project);

        return target;
    }

    public IssueNode map(IssueNodeDO source) {
        if(source == null) {
            return null;
        }
        final List<Issue> issues = this.mapDO(source.getIssues());
        final User user = userMapper.map(source.getAssignedTo());
        final Project project = projectMapper.map(source.getProject());
        if(source.getParent() != null) {
            ((IssueNodeDO) source.getParent()).setIssues(null);
        }
        final Issue parent = this.map(source.getParent());
        final IssueNode target = IssueNode.builder()
                .description(source.getDescription())
                .name(source.getName())
                .assignedTo(user)
                .estimatedTime(source.getEstimatedTime() == null ? 0 : source.getEstimatedTime().toHours())
                .childrens(issues)
                .type(source.getType().getText())
                .build();

        target.setId(source.getId());
        target.setParent(parent);
        target.setProject(project);

        return target;
    }

    public IssueLeafDO map(IssueLeaf source) {
        if(source == null) {
            return null;
        }
        final UserDO user = userMapper.map(source.getAssignedTo());
        final ProjectDO project = projectMapper.map(source.getProject());
        if(source.getParent() != null) {
            ((IssueNode) source.getParent()).setChildrens(null);
        }
        final List<SpendTimeDO> spentTime = spentTimeMapper.map(source.getSpentTime());
        final IssueDO parent = this.map(source.getParent());
        return IssueLeafDO.builder()
                .id(source.getId())
                .description(source.getDescription())
                .name(source.getName())
                .assignedTo(user)
                .type(IssueTypeDO.fromString(source.getType()))
                .parent((IssueNodeDO) parent)
                .spentTime(spentTime)
                .project(project)
                .estimatedTime(Duration.ofHours(source.getEstimatedTime() == null ? 0 : source.getEstimatedTime()))
                .build();
    }

    public IssueNodeDO map(IssueNode source) {
        if(source == null) {
            return null;
        }
        final UserDO user = userMapper.map(source.getAssignedTo());
        final List<IssueDO> issues = this.map(source.getChildrens());
        final ProjectDO project = projectMapper.map(source.getProject());
        if(source.getParent() != null) {
            ((IssueNode)source.getParent()).setChildrens(null);
        }
        final IssueDO parent = this.map(source.getParent());
        return IssueNodeDO.builder()
                    .id(source.getId())
                    .description(source.getDescription())
                    .name(source.getName())
                    .assignedTo(user)
                    .issues(issues)
                    .parent((IssueNodeDO) parent)
                    .estimatedTime(Duration.ofHours(source.getEstimatedTime() == null ? 0 : source.getEstimatedTime()))
                    .project(project)
                    .type(IssueTypeDO.fromString(source.getType()))
                    .build();
    }

}
