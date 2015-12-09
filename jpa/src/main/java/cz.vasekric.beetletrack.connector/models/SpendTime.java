package cz.vasekric.beetletrack.connector.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by vasek on 04.10.2015.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpendTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    private Long time;

    @ManyToOne
    private Issue issue;
}
