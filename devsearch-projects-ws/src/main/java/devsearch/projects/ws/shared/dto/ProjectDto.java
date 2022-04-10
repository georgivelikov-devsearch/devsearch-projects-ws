package devsearch.projects.ws.shared.dto;

import java.util.List;

public class ProjectDto {

    private String projectId;

    private String projectName;

    private String authorUsername;

    private String authorFullname;

    private String developerId;

    private String about;

    private String sourceCode;

    private Integer possitiveFeedback;

    private Integer negativeFeedback;

    private List<TagDto> tags;

    private List<CommentDto> comments;

    private String projectPictureUrl;

    private String publicKey;

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

    public List<TagDto> getTags() {
	return tags;
    }

    public void setTags(List<TagDto> tags) {
	this.tags = tags;
    }

    public List<CommentDto> getComments() {
	return comments;
    }

    public void setComments(List<CommentDto> comments) {
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
