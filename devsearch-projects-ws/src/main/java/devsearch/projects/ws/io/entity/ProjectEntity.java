package devsearch.projects.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}
