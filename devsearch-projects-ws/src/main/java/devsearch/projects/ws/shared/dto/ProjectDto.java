package devsearch.projects.ws.shared.dto;

import java.util.List;

public class ProjectDto {

    private String projectId;

    private String name;

    private String author;

    private String about;

    private String sourceCode;

    private String developerId;

    private Integer possitiveFeedback;

    private Integer negativeFeedback;

    private List<TagDto> tags;

    private List<CommentDto> comments;

    private String projectPictureUrl;

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
}
