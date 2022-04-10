package devsearch.projects.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = -6761956518237063897L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String projectId;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String about;

    @Column(nullable = true)
    private String sourceCode;

    @Column(nullable = false)
    private String developerId;

    @Column(nullable = true)
    private Integer possitiveFeedback;

    @Column(nullable = true)
    private Integer negativeFeedback;

    @OneToMany(mappedBy = "project")
    private List<TagEntity> tags;

    @OneToMany(mappedBy = "project")
    private List<CommentEntity> comments;
}
