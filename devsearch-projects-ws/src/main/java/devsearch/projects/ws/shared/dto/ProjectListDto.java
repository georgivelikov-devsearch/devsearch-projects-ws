package devsearch.projects.ws.shared.dto;

import java.util.Collection;

public class ProjectListDto {

    private int totalPages;
    private Collection<ProjectDto> projects;

    public int getTotalPages() {
	return totalPages;
    }

    public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
    }

    public Collection<ProjectDto> getProjects() {
	return projects;
    }

    public void setProjects(Collection<ProjectDto> projects) {
	this.projects = projects;
    }
}
