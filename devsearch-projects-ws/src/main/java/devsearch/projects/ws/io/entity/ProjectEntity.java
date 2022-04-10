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
    private String projectName;

    @Column(nullable = false)
    private String authorUsername;

    @Column(nullable = true)
    private String authorFullname;

    @Column(nullable = false)
    private String developerId;

    @Column(nullable = false)
    private String about;

    @Column(nullable = true)
    private String sourceCode;

    @Column(nullable = true)
    private Integer possitiveFeedback;

    @Column(nullable = true)
    private Integer negativeFeedback;

    @OneToMany(mappedBy = "project")
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "project")
    private List<CommentEntity> comments;

    @Column(nullable = true, length = 200)
    private String projectPictureUrl;

    @Column(nullable = false, unique = true)
    private String publicKey;

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

    public String getProjectName() {
	return projectName;
    }

    public void setProjectName(String projectName) {
	this.projectName = projectName;
    }

    public String getAuthorUsername() {
	return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
	this.authorUsername = authorUsername;
    }

    public String getAuthorFullname() {
	return authorFullname;
    }

    public void setAuthorFullname(String authorFullname) {
	this.authorFullname = authorFullname;
    }

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
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

    public String getProjectPictureUrl() {
	return projectPictureUrl;
    }

    public void setProjectPictureUrl(String projectPictureUrl) {
	this.projectPictureUrl = projectPictureUrl;
    }

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }
}
