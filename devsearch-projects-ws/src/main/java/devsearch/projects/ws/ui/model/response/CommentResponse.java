package devsearch.projects.ws.ui.model.response;

public class CommentResponse {

    private String commentId;

    private String author;

    private String content;

    private String publicKey;

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

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }

}
