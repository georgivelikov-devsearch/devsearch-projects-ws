package devsearch.projects.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import devsearch.common.exception.DevsearchApiException;
import devsearch.common.utils.Utils;
import devsearch.projects.ws.io.entity.ProjectEntity;
import devsearch.projects.ws.io.repository.ProjectRepository;
import devsearch.projects.ws.service.ProjectService;
import devsearch.projects.ws.shared.dto.ProjectDto;
import devsearch.projects.ws.shared.dto.ProjectListDto;
import devsearch.projects.ws.shared.mapper.ModelMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDto getProjectByProjectId(String projectId) {
	ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);

	return mapper.map(projectEntity, ProjectDto.class);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) throws DevsearchApiException {
	ProjectEntity projectEntity = mapper.map(projectDto, ProjectEntity.class);
	projectEntity.setProjectId(Utils.generateId());
	projectEntity.setPublicKey(Utils.generatePublicKey());
	ProjectEntity newProjectEntity = projectRepository.save(projectEntity);

	return mapper.map(newProjectEntity, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) throws DevsearchApiException {
	ProjectEntity projectEntity = projectRepository.findByProjectId(projectDto.getProjectId());

	projectEntity.setProjectName(projectDto.getProjectName());
	projectEntity.setAbout(projectDto.getAbout());
	projectEntity.setAuthorUsername(projectDto.getAuthorUsername());
	projectEntity.setAuthorFullname(projectDto.getAuthorFullname());
	projectEntity.setSourceCode(projectDto.getSourceCode());

	ProjectEntity updatedProjectEntity = projectRepository.save(projectEntity);

	return mapper.map(updatedProjectEntity, ProjectDto.class);
    }

    @Override
    public void deleteProject(String projectId) throws DevsearchApiException {
	// Check if underlying user is deleted?
	ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);
	if (projectEntity == null) {
	    throw new DevsearchApiException("No record found with this Id");
	}

	try {
	    projectRepository.delete(projectEntity);
	} catch (Exception ex) {
	    throw new DevsearchApiException("Failed to delete record", ex.getMessage());
	}
    }

    @Override
    public List<ProjectDto> getProjectsForDeveloper(String username) {
	List<ProjectDto> returnValue = new ArrayList<ProjectDto>();
	List<ProjectEntity> projectsForDeveloper = projectRepository.findAllByAuthorUsername(username);
	for (ProjectEntity projectEntity : projectsForDeveloper) {
	    ProjectDto projectDto = mapper.map(projectEntity, ProjectDto.class);
	    returnValue.add(projectDto);
	}

	return returnValue;
    }

    @Override
    public ProjectListDto getAllProjects(int page, int limit, String searchText) throws DevsearchApiException {
	ProjectListDto returnValue = new ProjectListDto();
	Pageable pageableRequest = PageRequest.of(page, limit);
	Page<ProjectEntity> projectListPage = null;

	if (searchText != null && !searchText.equals("")) {
	    projectListPage = projectRepository.findAllByText(pageableRequest, searchText);
	} else {
	    projectListPage = projectRepository.findAll(pageableRequest);
	}

	List<ProjectEntity> projects = projectListPage.getContent();
	int totalPages = projectListPage.getTotalPages();

	List<ProjectDto> projectDtoList = new ArrayList<>();
	for (ProjectEntity projectEntity : projects) {
	    ProjectDto projectDto = mapper.map(projectEntity, ProjectDto.class);
	    projectDtoList.add(projectDto);
	}

	returnValue.setTotalPages(totalPages);
	returnValue.setProjects(projectDtoList);

	return returnValue;
    }

}
