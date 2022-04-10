package devsearch.projects.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectDto getProjectByProjectId(String projectId) {
	ProjectEntity projectEntity = projectRepository.findByProjectId(projectId);

	return modelMapper.map(projectEntity, ProjectDto.class);
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) throws DevsearchApiException {
	ProjectEntity projectEntity = modelMapper.map(projectDto, ProjectEntity.class);
	projectEntity.setProjectId(Utils.generatePublicId());
	ProjectEntity newProjectEntity = projectRepository.save(projectEntity);

	return modelMapper.map(newProjectEntity, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) throws DevsearchApiException {
	ProjectEntity projectEntity = projectRepository.findByProjectId(projectDto.getProjectId());

	projectEntity.setName(projectDto.getName());
	projectEntity.setAbout(projectDto.getAbout());
	projectEntity.setAuthor(projectDto.getAuthor());
	projectEntity.setSourceCode(projectDto.getSourceCode());

	ProjectEntity updatedProjectEntity = projectRepository.save(projectEntity);

	return modelMapper.map(updatedProjectEntity, ProjectDto.class);
    }

    @Override
    public void deleteProject(String projectId) throws DevsearchApiException {
	// TODO Auto-generated method stub

    }

    @Override
    public List<ProjectDto> getProjectsForDeveloper(String developerId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ProjectListDto getAllProjects(int page, int limit, String searchText) throws DevsearchApiException {
	// TODO Auto-generated method stub
	return null;
    }

}
