package cz.vasekric.beetletrack.restapi.mappers;

import cz.vasekric.beetletrack.api.models.IssueCommon;
import cz.vasekric.beetletrack.api.models.IssueType;
import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueLeafDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vasek on 11.12.2015.
 */
@Component
public class IssueMapper implements Mapper<IssueDO, IssueCommon> {

    @Autowired private UserMapper userMapper;
    @Autowired private SpentTimeMapper spentTimeMapper;

    @Override
    public IssueCommon map(IssueDO source) {
        return source instanceof IssueNodeDO ? this.map((IssueNodeDO) source) : this.map((IssueLeafDO) source);
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
}
