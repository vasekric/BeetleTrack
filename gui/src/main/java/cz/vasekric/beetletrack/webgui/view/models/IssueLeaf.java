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
public class IssueLeaf implements Issue {
    private Integer id;
    private String type;
    private String name;
    private String description;
    private User assignedTo;
    private List<SpendTime> spentTime = new ArrayList<>();
    private Duration estimatedTime = Duration.ZERO;
    private Issue parent;

    @java.beans.ConstructorProperties({"id", "type", "name", "description", "assignedTo", "spentTime", "estimatedTime", "parent"})
    public IssueLeaf(Integer id, String type, String name, String description, User assignedTo, List<SpendTime> spentTime, Duration estimatedTime, Issue parent) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.spentTime = spentTime;
        this.estimatedTime = estimatedTime;
        this.parent = parent;
    }

    public IssueLeaf() {
    }

    public static IssueLeafBuilder builder() {
        return new IssueLeafBuilder();
    }

    @Override
    public Duration getTotalSpentTime() {
        if(spentTime == null || spentTime.isEmpty()) {
            return Duration.ZERO;
        }
        return spentTime.stream()
                    .map(SpendTime::getTime)
                    .reduce(Duration.ZERO, Duration::plus);
    }

    @Override
    public boolean hasChildrens() {
        return false;
    }

    @Override
    public List<Issue> getChildrens() {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public boolean isNode() {
        return false;
    }

    public Integer getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public User getAssignedTo() {
        return this.assignedTo;
    }

    public List<SpendTime> getSpentTime() {
        return this.spentTime;
    }

    public Duration getEstimatedTime() {
        return this.estimatedTime;
    }

    public Issue getParent() {
        return this.parent;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setSpentTime(List<SpendTime> spentTime) {
        this.spentTime = spentTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setParent(Issue parent) {
        this.parent = parent;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IssueLeaf)) return false;
        final IssueLeaf other = (IssueLeaf) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.id;
        final Object other$id = other.id;
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.description;
        final Object other$description = other.description;
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$assignedTo = this.assignedTo;
        final Object other$assignedTo = other.assignedTo;
        if (this$assignedTo == null ? other$assignedTo != null : !this$assignedTo.equals(other$assignedTo))
            return false;
        final Object this$spentTime = this.spentTime;
        final Object other$spentTime = other.spentTime;
        if (this$spentTime == null ? other$spentTime != null : !this$spentTime.equals(other$spentTime)) return false;
        final Object this$estimatedTime = this.estimatedTime;
        final Object other$estimatedTime = other.estimatedTime;
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
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 0 : $name.hashCode());
        final Object $description = this.description;
        result = result * PRIME + ($description == null ? 0 : $description.hashCode());
        final Object $assignedTo = this.assignedTo;
        result = result * PRIME + ($assignedTo == null ? 0 : $assignedTo.hashCode());
        final Object $spentTime = this.spentTime;
        result = result * PRIME + ($spentTime == null ? 0 : $spentTime.hashCode());
        final Object $estimatedTime = this.estimatedTime;
        result = result * PRIME + ($estimatedTime == null ? 0 : $estimatedTime.hashCode());
        final Object $parent = this.parent;
        result = result * PRIME + ($parent == null ? 0 : $parent.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof IssueLeaf;
    }

    public String toString() {
        return "cz.vasekric.beetletrack.webgui.view.models.IssueLeaf(id=" + this.id + ", type=" + this.type + ", name=" + this.name + ", description=" + this.description + ", assignedTo=" + this.assignedTo + ", spentTime=" + this.spentTime + ", estimatedTime=" + this.estimatedTime + ", parent=" + this.parent + ")";
    }

    public static class IssueLeafBuilder {
        private Integer id;
        private String type;
        private String name;
        private String description;
        private User assignedTo;
        private List<SpendTime> spentTime;
        private Duration estimatedTime;
        private Issue parent;

        IssueLeafBuilder() {
        }

        public IssueLeaf.IssueLeafBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder type(String type) {
            this.type = type;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder name(String name) {
            this.name = name;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder description(String description) {
            this.description = description;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder assignedTo(User assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder spentTime(List<SpendTime> spentTime) {
            this.spentTime = spentTime;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder estimatedTime(Duration estimatedTime) {
            this.estimatedTime = estimatedTime;
            return this;
        }

        public IssueLeaf.IssueLeafBuilder parent(Issue parent) {
            this.parent = parent;
            return this;
        }

        public IssueLeaf build() {
            return new IssueLeaf(id, type, name, description, assignedTo, spentTime, estimatedTime, parent);
        }

        public String toString() {
            return "cz.vasekric.beetletrack.webgui.view.models.IssueLeaf.IssueLeafBuilder(id=" + this.id + ", type=" + this.type + ", name=" + this.name + ", description=" + this.description + ", assignedTo=" + this.assignedTo + ", spentTime=" + this.spentTime + ", estimatedTime=" + this.estimatedTime + ", parent=" + this.parent + ")";
        }
    }
}
