package cz.vasekric.beetletrack.domain.models;

import lombok.*;

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
public class ProjectDO {
    private Integer id;
    private String name;
    private UserDO projectManager;
    private UserDO users;
    private List<IssueDO> issues = new ArrayList<>();


}
