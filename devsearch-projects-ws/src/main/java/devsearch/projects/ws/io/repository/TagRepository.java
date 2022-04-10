package devsearch.projects.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devsearch.projects.ws.io.entity.TagEntity;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {

    public TagEntity findByTagId(String tagId);
}
