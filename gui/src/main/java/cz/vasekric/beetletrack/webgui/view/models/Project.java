package cz.vasekric.beetletrack.webgui.view.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@ManagedBean
@RequestScoped
public class Project {
    private Integer id;
    private String name;
    private User projectManager;
    private String readme;
    private List<User> users = new ArrayList<>();
    private List<Issue> issues = new ArrayList<>();

    public Project(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @java.beans.ConstructorProperties({"id", "name", "projectManager", "readme", "users", "issues"})
    public Project(Integer id, String name, User projectManager, String readme, List<User> users, List<Issue> issues) {
        this.id = id;
        this.name = name;
        this.projectManager = projectManager;
        this.readme = readme;
        this.users = users;
        this.issues = issues;
    }

    public Project() {
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return !(id != null ? !id.equals(project.id) : project.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getProjectManager() {
        return this.projectManager;
    }

    public String getReadme() {
        return this.readme;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public List<Issue> getIssues() {
        return this.issues;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public static class ProjectBuilder {
        private Integer id;
        private String name;
        private User projectManager;
        private String readme;
        private List<User> users;
        private List<Issue> issues;

        ProjectBuilder() {
        }

        public Project.ProjectBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public Project.ProjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Project.ProjectBuilder projectManager(User projectManager) {
            this.projectManager = projectManager;
            return this;
        }

        public Project.ProjectBuilder readme(String readme) {
            this.readme = readme;
            return this;
        }

        public Project.ProjectBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Project.ProjectBuilder issues(List<Issue> issues) {
            this.issues = issues;
            return this;
        }

        public Project build() {
            return new Project(id, name, projectManager, readme, users, issues);
        }

        public String toString() {
            return "cz.vasekric.beetletrack.webgui.view.models.Project.ProjectBuilder(id=" + this.id + ", name=" + this.name + ", projectManager=" + this.projectManager + ", readme=" + this.readme + ", users=" + this.users + ", issues=" + this.issues + ")";
        }
    }
}
