package devsearch.projects.ws.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import devsearch.projects.ws.io.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long> {

    public ProjectEntity findByProjectId(String projectId);

    /*
     * @Transactional
     * 
     * @Query(value =
     * "SELECT d FROM DeveloperEntity d where (d.firstName LIKE %:searchText% OR d.lastName LIKE %:searchText%)"
     * ) Page<DeveloperEntity> findAllByText(Pageable pageable, @Param("searchText")
     * String searchText);
     */
}
