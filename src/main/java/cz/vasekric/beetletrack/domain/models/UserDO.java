package cz.vasekric.beetletrack.domain.models;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by vasek on 03.10.2015.
 */
@Data
@Builder
public class UserDO implements Serializable {

    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private boolean  authenticated;
}
