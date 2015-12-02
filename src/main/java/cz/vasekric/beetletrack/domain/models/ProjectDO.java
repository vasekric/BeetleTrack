package cz.vasekric.beetletrack.domain.models;

import cz.vasekric.beetletrack.webgui.view.models.Issue;
import cz.vasekric.beetletrack.webgui.view.models.User;
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
    private String readme;
    private UserDO projectManager;
    private List<User> users = new ArrayList<>();
    private List<IssueDO> issues = new ArrayList<>();


}
