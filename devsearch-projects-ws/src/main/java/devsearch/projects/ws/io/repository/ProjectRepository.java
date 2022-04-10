package devsearch.projects.ws.io.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devsearch.projects.ws.io.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long> {

    public ProjectEntity findByProjectId(String projectId);

    @Transactional
    @Query(value = "SELECT p FROM ProjectEntity p where p.projectName LIKE %:searchText%")
    Page<ProjectEntity> findAllByText(Pageable pageable, @Param("searchText") String searchText);

    @Transactional
    @Query(value = "SELECT p FROM ProjectEntity p where p.authorUsername = :authorUsername")
    List<ProjectEntity> findAllByDeveloperId(@Param("authorUsername") String authorUsername);
}
