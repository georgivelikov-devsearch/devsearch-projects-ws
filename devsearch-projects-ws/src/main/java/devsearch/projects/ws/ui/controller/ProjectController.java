package devsearch.projects.ws.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devsearch.common.exception.DevsearchApiException;
import devsearch.projects.ws.service.ProjectService;
import devsearch.projects.ws.shared.dto.ProjectDto;
import devsearch.projects.ws.shared.mapper.ModelMapper;
import devsearch.projects.ws.ui.model.request.ProjectRequest;
import devsearch.projects.ws.ui.model.response.ProjectResponse;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectService projectService;

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
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) throws DevsearchApiException {
	ProjectDto projectDto = mapper.map(projectRequest, ProjectDto.class);
	ProjectDto createdProjectDto = projectService.createProject(projectDto);

	return mapper.map(createdProjectDto, ProjectResponse.class);
    }

    @PutMapping()
    public ProjectResponse updateProject(@RequestBody ProjectRequest projectRequest) throws DevsearchApiException {
	ProjectDto projectDto = mapper.map(projectRequest, ProjectDto.class);
	// TODO Add image logic with ImageClient
	ProjectDto updatedProjectDto = projectService.createProject(projectDto);

	return mapper.map(updatedProjectDto, ProjectResponse.class);
    }
}
