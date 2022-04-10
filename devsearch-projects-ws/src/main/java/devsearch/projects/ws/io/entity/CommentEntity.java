package devsearch.projects.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CommentEntity implements Serializable {

    private static final long serialVersionUID = -8254950179660347040L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String commentId;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

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

    public String getCommentId() {
	return commentId;
    }

    public void setCommentId(String commentId) {
	this.commentId = commentId;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
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
