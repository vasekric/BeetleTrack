package cz.vasekric.beetletrack.domain.models;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDO implements Serializable {
    private Integer id;
    private String name;
    private String readme;
    private UserDO projectManager;
    private List<UserDO> users = new ArrayList<>();
    private List<IssueDO> issues = new ArrayList<>();

}
