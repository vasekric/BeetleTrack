package cz.vasekric.beetletrack.webgui.view.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasek on 04.10.2015.
 */
@ManagedBean
@RequestScoped
public class IssueNode implements Issue {
    private Integer id;
    private String name;
    private String description;
    private String tags;
    private String type;
    private User assignedTo;
    private List<Issue> childrens = new ArrayList<>();
    private Duration estimatedTime;
    private Issue parent;

    @java.beans.ConstructorProperties({"id", "name", "description", "tags", "type", "assignedTo", "childrens", "estimatedTime", "parent"})
    public IssueNode(Integer id, String name, String description, String tags, String type, User assignedTo, List<Issue> childrens, Duration estimatedTime, Issue parent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.assignedTo = assignedTo;
        this.childrens = childrens;
        this.estimatedTime = estimatedTime;
        this.parent = parent;
    }

    public IssueNode() {
    }

    public static IssueNodeBuilder builder() {
        return new IssueNodeBuilder();
    }

    @Override
    public Duration getEstimatedTime() {
        if(estimatedTime != null) {
            return estimatedTime;
        }
        if(this.hasChildrens()) {
            return getChildrens().stream()
                    .map(Issue::getEstimatedTime)
                    .reduce(Duration.ZERO, Duration::plus);
        }
        return Duration.ZERO;
    }

    @Override
    public boolean hasChildrens() {
        return childrens != null && !childrens.isEmpty();
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean isNode() {
        return true;
    }

    @Override
    public Duration getTotalSpentTime() {
        if(this.hasChildrens()) {
            return getChildrens().stream()
                    .map(Issue::getTotalSpentTime)
                    .reduce(Duration.ZERO, Duration::plus);
        }
        return Duration.ZERO;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTags() {
        return this.tags;
    }

    public String getType() {
        return this.type;
    }

    public User getAssignedTo() {
        return this.assignedTo;
    }

    public List<Issue> getChildrens() {
        return this.childrens;
    }

    public Issue getParent() {
        return this.parent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setChildrens(List<Issue> childrens) {
        this.childrens = childrens;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setParent(Issue parent) {
        this.parent = parent;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IssueNode)) return false;
        final IssueNode other = (IssueNode) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.id;
        final Object other$id = other.id;
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.description;
        final Object other$description = other.description;
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$tags = this.tags;
        final Object other$tags = other.tags;
        if (this$tags == null ? other$tags != null : !this$tags.equals(other$tags)) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$assignedTo = this.assignedTo;
        final Object other$assignedTo = other.assignedTo;
        if (this$assignedTo == null ? other$assignedTo != null : !this$assignedTo.equals(other$assignedTo))
            return false;
        final Object this$childrens = this.childrens;
        final Object other$childrens = other.childrens;
        if (this$childrens == null ? other$childrens != null : !this$childrens.equals(other$childrens)) return false;
        final Object this$estimatedTime = this.getEstimatedTime();
        final Object other$estimatedTime = other.getEstimatedTime();
        if (this$estimatedTime == null ? other$estimatedTime != null : !this$estimatedTime.equals(other$estimatedTime))
            return false;
        final Object this$parent = this.parent;
        final Object other$parent = other.parent;
        if (this$parent == null ? other$parent != null : !this$parent.equals(other$parent)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.id;
        result = result * PRIME + ($id == null ? 0 : $id.hashCode());
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 0 : $name.hashCode());
        final Object $description = this.description;
        result = result * PRIME + ($description == null ? 0 : $description.hashCode());
        final Object $tags = this.tags;
        result = result * PRIME + ($tags == null ? 0 : $tags.hashCode());
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        final Object $assignedTo = this.assignedTo;
        result = result * PRIME + ($assignedTo == null ? 0 : $assignedTo.hashCode());
        final Object $childrens = this.childrens;
        result = result * PRIME + ($childrens == null ? 0 : $childrens.hashCode());
        final Object $estimatedTime = this.getEstimatedTime();
        result = result * PRIME + ($estimatedTime == null ? 0 : $estimatedTime.hashCode());
        final Object $parent = this.parent;
        result = result * PRIME + ($parent == null ? 0 : $parent.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof IssueNode;
    }

    public String toString() {
        return "cz.vasekric.beetletrack.webgui.view.models.IssueNode(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", tags=" + this.tags + ", type=" + this.type + ", assignedTo=" + this.assignedTo + ", childrens=" + this.childrens + ", estimatedTime=" + this.getEstimatedTime() + ", parent=" + this.parent + ")";
    }

    public static class IssueNodeBuilder {
        private Integer id;
        private String name;
        private String description;
        private String tags;
        private String type;
        private User assignedTo;
        private List<Issue> childrens;
        private Duration estimatedTime;
        private Issue parent;

        IssueNodeBuilder() {
        }

        public IssueNode.IssueNodeBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public IssueNode.IssueNodeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public IssueNode.IssueNodeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public IssueNode.IssueNodeBuilder tags(String tags) {
            this.tags = tags;
            return this;
        }

        public IssueNode.IssueNodeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public IssueNode.IssueNodeBuilder assignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public IssueNode.IssueNodeBuilder childrens(List<Issue> childrens) {
            this.childrens = childrens;
            return this;
        }

        public IssueNode.IssueNodeBuilder estimatedTime(Duration estimatedTime) {
            this.estimatedTime = estimatedTime;
            return this;
        }

        public IssueNode.IssueNodeBuilder parent(Issue parent) {
            this.parent = parent;
            return this;
        }

        public IssueNode build() {
            return new IssueNode(id, name, description, tags, type, assignedTo, childrens, estimatedTime, parent);
        }

        public String toString() {
            return "cz.vasekric.beetletrack.webgui.view.models.IssueNode.IssueNodeBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", tags=" + this.tags + ", type=" + this.type + ", assignedTo=" + this.assignedTo + ", childrens=" + this.childrens + ", estimatedTime=" + this.estimatedTime + ", parent=" + this.parent + ")";
        }
    }
}
