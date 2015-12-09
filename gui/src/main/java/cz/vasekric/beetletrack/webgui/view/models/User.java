package cz.vasekric.beetletrack.webgui.view.models;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vasek on 03.10.2015.
 */
@Named
@SessionScoped
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private boolean authenticated;

    @java.beans.ConstructorProperties({"id", "username", "email", "fullName", "password", "authenticated"})
    public User(Integer id, String username, String email, String fullName, String password, boolean authenticated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.authenticated = authenticated;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.id;
        final Object other$id = other.id;
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$username = this.username;
        final Object other$username = other.username;
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.email;
        final Object other$email = other.email;
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$fullName = this.fullName;
        final Object other$fullName = other.fullName;
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) return false;
        final Object this$password = this.password;
        final Object other$password = other.password;
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        if (this.authenticated != other.authenticated) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.id;
        result = result * PRIME + ($id == null ? 0 : $id.hashCode());
        final Object $username = this.username;
        result = result * PRIME + ($username == null ? 0 : $username.hashCode());
        final Object $email = this.email;
        result = result * PRIME + ($email == null ? 0 : $email.hashCode());
        final Object $fullName = this.fullName;
        result = result * PRIME + ($fullName == null ? 0 : $fullName.hashCode());
        final Object $password = this.password;
        result = result * PRIME + ($password == null ? 0 : $password.hashCode());
        result = result * PRIME + (this.authenticated ? 79 : 97);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof User;
    }

    public String toString() {
        return "cz.vasekric.beetletrack.webgui.view.models.User(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", fullName=" + this.fullName + ", password=" + this.password + ", authenticated=" + this.authenticated + ")";
    }

    public static class UserBuilder {
        private Integer id;
        private String username;
        private String email;
        private String fullName;
        private String password;
        private boolean authenticated;

        UserBuilder() {
        }

        public User.UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public User.UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public User.UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User.UserBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public User.UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User.UserBuilder authenticated(boolean authenticated) {
            this.authenticated = authenticated;
            return this;
        }

        public User build() {
            return new User(id, username, email, fullName, password, authenticated);
        }

        public String toString() {
            return "cz.vasekric.beetletrack.webgui.view.models.User.UserBuilder(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", fullName=" + this.fullName + ", password=" + this.password + ", authenticated=" + this.authenticated + ")";
        }
    }
}
