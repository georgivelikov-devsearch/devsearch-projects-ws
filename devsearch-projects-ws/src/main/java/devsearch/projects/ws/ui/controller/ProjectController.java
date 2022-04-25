package devsearch.projects.ws.ui.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devsearch.common.exception.DevsearchApiException;
import devsearch.projects.ws.client.ImageClient;
import devsearch.projects.ws.security.jwt.JwtService;
import devsearch.projects.ws.service.ProjectService;
import devsearch.projects.ws.shared.dto.ProjectDto;
import devsearch.projects.ws.shared.dto.ProjectListDto;
import devsearch.projects.ws.shared.mapper.ModelMapper;
import devsearch.projects.ws.ui.model.request.ProjectImageRequest;
import devsearch.projects.ws.ui.model.request.ProjectRequest;
import devsearch.projects.ws.ui.model.response.ProjectImageResponse;
import devsearch.projects.ws.ui.model.response.ProjectListResponse;
import devsearch.projects.ws.ui.model.response.ProjectResponse;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private JwtService jwtService;

    @GetMapping(path = "/status")
    public String status() {
	return "ProjectController is working!";
    }

    @GetMapping(path = "/{projectId}")
    public ProjectResponse getProject(@PathVariable String projectId) throws DevsearchApiException {
	ProjectDto projectDto = projectService.getProjectByProjectId(projectId);

	return mapper.map(projectDto, ProjectResponse.class);
    }

    @PostMapping()
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest, @AuthenticationPrincipal Jwt jwt)
	    throws DevsearchApiException {
	// TODO !!! IMPORTANT !!!
	// 1. SET DEVELOPER-ID AS CLAIM IN JWT-BUT IT COULD BE SEEN FROM FRONTEND?
	checkAuthorOrigin(projectRequest, jwt, "createProject");

	ProjectDto projectDto = mapper.map(projectRequest, ProjectDto.class);
	String projectPictureBackUp = projectDto.getProjectPictureUrl();
	projectDto.setProjectPictureUrl(null);

	ProjectDto newProjectDto = projectService.createProject(projectDto);

	if (projectRequest.isNewProjectPictureUpload()) {
	    newProjectDto.setProjectPictureUrl(projectPictureBackUp);
	    newProjectDto = setProjectPicture(newProjectDto);
	}

	newProjectDto = projectService.updateProject(newProjectDto);

	return mapper.map(newProjectDto, ProjectResponse.class);
    }

    @PutMapping()
    public ProjectResponse updateProject(@RequestBody ProjectRequest projectRequest, @AuthenticationPrincipal Jwt jwt)
	    throws DevsearchApiException {
	checkAuthorOrigin(projectRequest, jwt, "updateProject");

	ProjectDto projectDto = mapper.map(projectRequest, ProjectDto.class);

	if (projectRequest.isNewProjectPictureUpload()) {
	    projectDto = setProjectPicture(projectDto);
	}

	ProjectDto updatedProjectDto = projectService.updateProject(projectDto);

	return mapper.map(updatedProjectDto, ProjectResponse.class);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectId,
	    @RequestParam(value = "username", defaultValue = "") String username, @AuthenticationPrincipal Jwt jwt)
	    throws DevsearchApiException {
	checkAuthorOrigin(username, jwt, "deleteProject");

	projectService.deleteProject(projectId);
	return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/public/all")
    public ProjectListResponse getProjects(@RequestParam(value = "page", defaultValue = "1") int page,
	    @RequestParam(value = "limit", defaultValue = "6") int limit,
	    @RequestParam(value = "searchText", defaultValue = "") String searchText) throws DevsearchApiException {

	// In the Repository implementation pagination starts with '0', but in UI
	// usually pages start from 1, 2, 3 etc. So UI will send the number of the page,
	// which should be reduced by 1
	if (page > 0) {
	    page -= 1;
	}

	ProjectListDto projectListDto = projectService.getAllProjects(page, limit, searchText);
	Collection<ProjectResponse> projectListResponse = new ArrayList<ProjectResponse>();
	for (ProjectDto projectDto : projectListDto.getProjects()) {
	    ProjectResponse projectResponse = mapper.map(projectDto, ProjectResponse.class);
	    projectResponse.setProjectId(null);
	    projectListResponse.add(projectResponse);
	}

	ProjectListResponse response = new ProjectListResponse();
	response.setTotalPages(projectListDto.getTotalPages());
	response.setProjects(projectListResponse);

	return response;
    }

    @GetMapping("/public/all/{username}")
    public List<ProjectResponse> getProjectsForDeveloperPublic(@PathVariable String username)
	    throws DevsearchApiException {
	// TODO Fix security for this
	// checkAuthorOrigin(username, jwt, "getProjectsForDeveloper");

	List<ProjectDto> projectListDto = projectService.getProjectsForDeveloperByUsername(username);
	List<ProjectResponse> projectListResponse = new ArrayList<ProjectResponse>();
	for (ProjectDto projectDto : projectListDto) {
	    ProjectResponse projectResponse = mapper.map(projectDto, ProjectResponse.class);
	    projectResponse.setProjectId(null);
	    projectListResponse.add(projectResponse);
	}

	return projectListResponse;
    }

    @GetMapping("/all/{developerId}")
    public List<ProjectResponse> getProjectsForDeveloper(@PathVariable String developerId,
	    @AuthenticationPrincipal Jwt jwt) throws DevsearchApiException {
	// TODO Fix security for this
	// checkAuthorOrigin(username, jwt, "getProjectsForDeveloper");

	List<ProjectDto> projectListDto = projectService.getProjectsForDeveloper(developerId);
	List<ProjectResponse> projectListResponse = new ArrayList<ProjectResponse>();
	for (ProjectDto projectDto : projectListDto) {
	    ProjectResponse projectResponse = mapper.map(projectDto, ProjectResponse.class);
	    projectListResponse.add(projectResponse);
	}

	return projectListResponse;
    }

    private void checkAuthorOrigin(ProjectRequest projectRequest, Jwt jwt, String method) throws DevsearchApiException {
	String requestUsername = projectRequest.getAuthorUsername();
	checkAuthorOrigin(requestUsername, jwt, method);
    }

    private void checkAuthorOrigin(String username, Jwt jwt, String method) throws DevsearchApiException {
	String authenticatedUsername = jwtService.getUsername(jwt);
	if (!authenticatedUsername.equals(username)) {
	    throw new DevsearchApiException("Unauthenticated attemp to call " + method + " method!");
	}
    }

    private ProjectDto setProjectPicture(ProjectDto projectDto) {
	try {
	    ProjectImageRequest imageRequest = new ProjectImageRequest();
	    imageRequest.setProjectId(projectDto.getProjectId());
	    imageRequest.setProjectPictureUrl(projectDto.getProjectPictureUrl());

	    ResponseEntity<ProjectImageResponse> imageResponse = imageClient.addProjectImage(imageRequest);
	    String projectPictureUrl = imageResponse.getBody().getPictureUrl();
	    projectDto.setProjectPictureUrl(projectPictureUrl);
	} catch (Exception ex) {
	    // Log exception
	}

	return projectDto;
    }
}
