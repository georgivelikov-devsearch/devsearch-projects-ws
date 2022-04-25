package devsearch.projects.ws.ui.model.request;

public class ProjectImageRequest {
    private String projectId;
    private String projectPictureUrl;

    public String getProjectId() {
	return projectId;
    }

    public void setProjectId(String projectId) {
	this.projectId = projectId;
    }

    public String getProjectPictureUrl() {
	return projectPictureUrl;
    }

    public void setProjectPictureUrl(String projectPictureUrl) {
	this.projectPictureUrl = projectPictureUrl;
    }

}
