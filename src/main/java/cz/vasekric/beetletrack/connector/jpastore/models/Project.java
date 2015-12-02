package cz.vasekric.beetletrack.connector.jpastore.models;

import cz.vasekric.beetletrack.domain.models.UserDO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String readme;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "project")
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Issue> issues;
}
