package cz.vasekric.beetletrack.webgui.view.controllers;

import cz.vasekric.beetletrack.domain.models.IssueDO;
import cz.vasekric.beetletrack.domain.models.IssueNodeDO;
import cz.vasekric.beetletrack.service.IssueService;
import cz.vasekric.beetletrack.webgui.view.mappers.IssueMapper;
import cz.vasekric.beetletrack.webgui.view.models.*;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vasek on 04.10.2015.
 */
@Named
@SessionScoped
@Data
public class IssuesController implements Serializable {

    @Inject private IssueService issueService;
    @Inject private IssueMapper issueMapper;

    public Issue getIssue(Integer id) {
        final IssueDO issue = issueService.getIssue(id);
        final Issue mappedIssue = issueMapper.map(issue);
        return mappedIssue;
    }

    public List<Issue> getIssuesByProjectId(Integer projectId) {
        List<IssueNodeDO> issueNodes = issueService.getAllByProjectId(projectId);
        final List<IssueDO> issues = issueNodes.stream()
                .map(issueNodeDO -> (IssueDO) issueNodeDO)
                .collect(Collectors.toList());
        return issueMapper.mapDO(issues);
    }

    public List<Issue> getIssueChildrens(Integer issueId) {
        final List<IssueNodeDO> issueDOs = issueService.getAllByIssueId(issueId);
        final List<IssueDO> issues = issueDOs.stream()
                .map(issueNodeDO -> (IssueDO) issueNodeDO)
                .collect(Collectors.toList());
        return issueMapper.mapDO(issues);
    }

    public String create(InputIssue issue, Integer projectId) {
        final IssueDO issueDO = issueMapper.map(issue);
        final IssueDO createdIssue = issueService.create(issueDO, projectId);
        System.out.println(createdIssue);

        return "project-issues.xhtml?projectId="+projectId+"&faces-redirect=true";
    }

    public void createChild(IssueNode issue, Integer issueId) {
        final IssueDO issueDO = issueMapper.map(issue);
        final IssueDO createdIssue = issueService.createChild(issueDO, issueId);
        System.out.println(createdIssue);
    }




    private Integer projectId;
    private List<Issue> issues;

    public void init() {
        System.out.println("projectId: "+projectId);

        issues = new ArrayList<>();

        final IssueNode node = new IssueNode();
        node.setId(1);
        node.setName("Some Epic");
        final List<Issue> nodeIssues = node.getChildrens();
        nodeIssues.add(
                IssueLeaf.builder()
                        .id(2)
                        .name("Some task")
                        .type("Task")
                        .description("Some description")
                        .build());
        nodeIssues.add(
                IssueLeaf.builder()
                        .id(3)
                        .name("Some task 2")
                        .type("Task")
                        .description("Some description 2")
                        .build());

        issues.add(node);

    }



    private Project selectedProject;

    public void goToIssues(Project project) {
        this.selectedProject = project;
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "issues.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Issue> getIssuesByProject() {
        System.out.println(selectedProject);
        final List<Issue> issues = new ArrayList<>();

        final IssueNode node = new IssueNode();
        node.setId(1);
        node.setName("Some Epic");
        final List<Issue> nodeIssues = node.getChildrens();
        nodeIssues.add(
                IssueLeaf.builder()
                        .id(2)
                        .name("Some task")
                        .type("Task")
                        .description("Some description")
                        .build());
        nodeIssues.add(
                IssueLeaf.builder()
                        .id(3)
                        .name("Some task 2")
                        .type("Task")
                        .description("Some description 2")
                        .build());

        issues.add(node);

        return issues;
    }
}
