package devsearch.projects.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class TagEntity implements Serializable {

    private static final long serialVersionUID = 9177533359693655330L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String tagId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(nullable = false, unique = true)
    private String publicKey;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getTagId() {
	return tagId;
    }

    public void setTagId(String tagId) {
	this.tagId = tagId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ProjectEntity getProject() {
	return project;
    }

    public void setProject(ProjectEntity project) {
	this.project = project;
    }

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }
}
