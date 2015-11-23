package cz.vasekric.beetletrack.connector.jpastore.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Period;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Entity
public class SpendTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    private Period time;

    @ManyToOne
    private IssueLeaf issue;
}