package cz.vasekric.beetletrack.webgui.view.models;

import lombok.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
