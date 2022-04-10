package devsearch.projects.ws.service;

import java.util.List;

import devsearch.common.exception.DevsearchApiException;
import devsearch.projects.ws.shared.dto.ProjectDto;
import devsearch.projects.ws.shared.dto.ProjectListDto;

public interface ProjectService {

    public ProjectDto getProjectByProjectId(String projectId);

    public ProjectDto createProject(ProjectDto projectDto) throws DevsearchApiException;

    public ProjectDto updateProject(ProjectDto projectDto) throws DevsearchApiException;

    public void deleteProject(String projectId) throws DevsearchApiException;

    public List<ProjectDto> getProjectsForDeveloper(String username);

    public ProjectListDto getAllProjects(int page, int limit, String searchText) throws DevsearchApiException;

}
