package cz.vasekric.beetletrack.webgui.view.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vasek on 03.10.2015.
 */
@Named
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private boolean authenticated;
}
