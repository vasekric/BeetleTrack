package cz.vasekric.beetletrack.connector.jpastore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Issue {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Project project;

    @ManyToOne
    private Issue parent;
}
