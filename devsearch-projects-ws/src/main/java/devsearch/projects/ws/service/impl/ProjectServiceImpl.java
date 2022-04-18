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
import devsearch.projects.ws.io.entity.TagEntity;
import devsearch.projects.ws.io.repository.ProjectRepository;
import devsearch.projects.ws.io.repository.TagRepository;
import devsearch.projects.ws.service.ProjectService;
import devsearch.projects.ws.shared.dto.ProjectDto;
import devsearch.projects.ws.shared.dto.ProjectListDto;
import devsearch.projects.ws.shared.dto.TagDto;
import devsearch.projects.ws.shared.mapper.ModelMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagRepository tagRepository;

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
	projectEntity.setTags(new ArrayList<>());
	ProjectEntity newProjectEntity = projectRepository.save(projectEntity);

	for (int i = 0; i < projectDto.getTags().size(); i++) {
	    TagDto tagDto = projectDto.getTags().get(i);
	    TagEntity tagEntity = mapper.map(tagDto, TagEntity.class);
	    tagEntity.setTagId(Utils.generateId());
	    tagEntity.setPublicKey(Utils.generatePublicKey());
	    tagEntity.setPosition(i);
	    tagEntity.setProject(newProjectEntity);
	    TagEntity newTagEntity = tagRepository.save(tagEntity);
	    projectEntity.getTags().add(newTagEntity);
	}

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

	for (int i = 0; i < updatedProjectEntity.getTags().size(); i++) {
	    TagEntity tagEntity = updatedProjectEntity.getTags().get(i);
	    // Request does not contain the tag, so it has been deleted in UI and must be
	    // deleted here as well
	    if (!projectDto.getTags().stream().anyMatch(t -> t.getTagId().equals(tagEntity.getTagId()))) {
		tagRepository.delete(tagEntity);
	    }
	}

	for (int i = 0; i < projectDto.getTags().size(); i++) {
	    TagDto tagDto = projectDto.getTags().get(i);
	    TagEntity tagEntity = null;
	    if (tagDto.getTagId() == null) {
		tagEntity = mapper.map(tagDto, TagEntity.class);
		tagEntity.setTagId(Utils.generateId());
		tagEntity.setPublicKey(Utils.generatePublicKey());
		tagEntity.setProject(updatedProjectEntity);
	    } else {
		tagEntity = tagRepository.findByTagId(tagDto.getTagId());
	    }

	    tagEntity.setPosition(i);
	    TagEntity newTagEntity = tagRepository.save(tagEntity);
	}

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
    public List<ProjectDto> getProjectsForDeveloper(String developerId) {
	List<ProjectDto> returnValue = new ArrayList<ProjectDto>();
	List<ProjectEntity> projectsForDeveloper = projectRepository.findAllByDeveloperId(developerId);
	for (ProjectEntity projectEntity : projectsForDeveloper) {
	    ProjectDto projectDto = mapper.map(projectEntity, ProjectDto.class);
	    returnValue.add(projectDto);
	}

	return returnValue;
    }

    @Override
    public List<ProjectDto> getProjectsForDeveloperByUsername(String username) {
	List<ProjectDto> returnValue = new ArrayList<ProjectDto>();
	List<ProjectEntity> projectsForDeveloper = projectRepository.findAllByUsername(username);
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
