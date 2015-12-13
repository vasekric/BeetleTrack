package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.IssueCommon;
import cz.vasekric.beetletrack.api.models.IssueType;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueLeafDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.domain.models.IssueTypeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 11.12.2015.
 */
@Component
public class IssueMapper implements Mapper<IssueDO, IssueCommon> {

    @Autowired private UserMapper userMapper;
    @Autowired private SpentTimeMapper spentTimeMapper;
    @Autowired private ProjectMapper projectMapper;

    @Override
    public IssueCommon map(IssueDO source) {
        return source instanceof IssueNodeDO ? this.map((IssueNodeDO) source) : this.map((IssueLeafDO) source);
    }

    public IssueDO map(IssueCommon source) {
        if(source == null) {
            return null;
        }
        if(source.clazz.equals("IssueNode")) {
            return this.mapNode(source);
        }
        else {
            return this.mapLeaf(source);
        }

    }

    public IssueLeafDO mapLeaf(IssueCommon source) {
        if(source == null) {
            return null;
        }
        return IssueLeafDO.builder()
                .id(source.id)
                .assignedTo(userMapper.map(source.assignedTo))
                .type(IssueTypeDO.fromString(source.type.getText()))
                .description(source.description)
                .estimatedTime(source.estimatedTime != null ? Duration.ofHours(source.estimatedTime) : Duration.ZERO)
                .parent(this.mapNode(source.parent))
                .project(projectMapper.map(source.project))
                .name(source.name)
                .build();
    }

    public IssueNodeDO mapNode(IssueCommon source) {
        if(source == null) {
            return null;
        }
        return IssueNodeDO.builder()
                .id(source.id)
                .assignedTo(userMapper.map(source.assignedTo))
                .type(IssueTypeDO.fromString(source.type.getText()))
                .description(source.description)
                .estimatedTime(source.estimatedTime != null ? Duration.ofHours(source.estimatedTime) : Duration.ZERO)
                .issues(new ArrayList<>())
                .parent(this.mapNode(source.parent))
                .project(projectMapper.map(source.project))
                .name(source.name)
                .tags(source.tags)
                .build();
    }

    public IssueCommon map(IssueLeafDO source) {
        if(source == null) {
            return null;
        }
        final IssueCommon issue = new IssueCommon();
        issue.id = source.getId();
        issue.name = source.getName();
        issue.estimatedTime = source.getEstimatedTime().toHours();
        issue.description = source.getDescription();
        issue.parent = this.map(source.getParent());
        issue.type = source.getType() != null ? IssueType.fromString(source.getType().getText()) : null;
        issue.assignedTo = userMapper.map(source.getAssignedTo());
        issue.spentTime = spentTimeMapper.map(source.getSpentTime());
        issue.clazz = "IssueLeaf";

        return issue;
    }

    public IssueCommon map(IssueNodeDO source) {
        if(source == null) {
            return null;
        }
        final IssueCommon issue = new IssueCommon();
        issue.id = source.getId();
        issue.name = source.getName();
        issue.estimatedTime = source.getEstimatedTime().toHours();
        issue.description = source.getDescription();
        issue.parent = this.map(source.getParent());
        issue.type = source.getType() != null ? IssueType.fromString(source.getType().getText()) : null;
        issue.clazz = "IssueNode";
        issue.assignedTo = userMapper.map(source.getAssignedTo());
        issue.children = this.map(source.getIssues());

        return issue;
    }

    public List<IssueDO> mapCom(List<IssueCommon> source) {
        if(source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(this::map).collect(Collectors.toList());
    }
}
