package devsearch.projects.ws.shared.dto;

public class CommentDto {

    private String commentId;

    private String author;

    private String content;

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

}
