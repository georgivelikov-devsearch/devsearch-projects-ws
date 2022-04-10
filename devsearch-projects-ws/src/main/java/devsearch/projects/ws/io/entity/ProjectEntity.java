package devsearch.projects.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = -6761956518237063897L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String projectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String about;

    @Column(nullable = true)
    private String sourceCode;

    @Column(nullable = false)
    private String developerId;

    @Column(nullable = true)
    private Integer possitiveFeedback;

    @Column(nullable = true)
    private Integer negativeFeedback;

    @OneToMany(mappedBy = "project")
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "project")
    private List<CommentEntity> comments;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getProjectId() {
	return projectId;
    }

    public void setProjectId(String projectId) {
	this.projectId = projectId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getAbout() {
	return about;
    }

    public void setAbout(String about) {
	this.about = about;
    }

    public String getSourceCode() {
	return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
	this.sourceCode = sourceCode;
    }

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
    }

    public Integer getPossitiveFeedback() {
	return possitiveFeedback;
    }

    public void setPossitiveFeedback(Integer possitiveFeedback) {
	this.possitiveFeedback = possitiveFeedback;
    }

    public Integer getNegativeFeedback() {
	return negativeFeedback;
    }

    public void setNegativeFeedback(Integer negativeFeedback) {
	this.negativeFeedback = negativeFeedback;
    }

    public List<TagEntity> getTags() {
	return tags;
    }

    public void setTags(List<TagEntity> tags) {
	this.tags = tags;
    }

    public List<CommentEntity> getComments() {
	return comments;
    }

    public void setComments(List<CommentEntity> comments) {
	this.comments = comments;
    }
}
