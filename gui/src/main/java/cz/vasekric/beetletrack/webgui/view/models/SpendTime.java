package cz.vasekric.beetletrack.webgui.view.models;

import java.time.Duration;

/**
 * Created by vasek on 04.10.2015.
 */
public class SpendTime {
    private final User user;
    private final Duration time;

    @java.beans.ConstructorProperties({"user", "time"})
    public SpendTime(User user, Duration time) {
        this.user = user;
        this.time = time;
    }

    public static SpendTimeBuilder builder() {
        return new SpendTimeBuilder();
    }

    public User getUser() {
        return this.user;
    }

    public Duration getTime() {
        return this.time;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SpendTime)) return false;
        final SpendTime other = (SpendTime) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$user = this.user;
        final Object other$user = other.user;
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final Object this$time = this.time;
        final Object other$time = other.time;
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $user = this.user;
        result = result * PRIME + ($user == null ? 0 : $user.hashCode());
        final Object $time = this.time;
        result = result * PRIME + ($time == null ? 0 : $time.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof SpendTime;
    }

    public String toString() {
        return "cz.vasekric.beetletrack.webgui.view.models.SpendTime(user=" + this.user + ", time=" + this.time + ")";
    }

    public static class SpendTimeBuilder {
        private User user;
        private Duration time;

        SpendTimeBuilder() {
        }

        public SpendTime.SpendTimeBuilder user(User user) {
            this.user = user;
            return this;
        }

        public SpendTime.SpendTimeBuilder time(Duration time) {
            this.time = time;
            return this;
        }

        public SpendTime build() {
            return new SpendTime(user, time);
        }

        public String toString() {
            return "cz.vasekric.beetletrack.webgui.view.models.SpendTime.SpendTimeBuilder(user=" + this.user + ", time=" + this.time + ")";
        }
    }
}
