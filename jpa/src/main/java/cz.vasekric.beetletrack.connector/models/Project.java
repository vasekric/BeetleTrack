package cz.vasekric.beetletrack.connector.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 10000)
    private String readme;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "project")
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Issue> issues;
}
