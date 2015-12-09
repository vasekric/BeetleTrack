package cz.vasekric.beetletrack.domain.models;

import java.io.Serializable;

/**
 * Created by vasek on 18.11.2015.
 */
public enum IssueTypeDO implements Serializable {
    EPIC("epic"),
    USER_STORY("user_story"),
    TASK("task");

    private String text;

    IssueTypeDO(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static IssueTypeDO fromString(String text) {
        if (text != null) {
            for (IssueTypeDO b : IssueTypeDO.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
