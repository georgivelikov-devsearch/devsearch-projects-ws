package devsearch.projects.ws.ui.model.response;

import java.util.Collection;

public class ProjectListResponse {

    private int totalPages;
    private Collection<ProjectResponse> projects;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<ProjectResponse> getProjects() {
	return projects;
    }

    public void setProjects(Collection<ProjectResponse> projects) {
	this.projects = projects;
    }
}
