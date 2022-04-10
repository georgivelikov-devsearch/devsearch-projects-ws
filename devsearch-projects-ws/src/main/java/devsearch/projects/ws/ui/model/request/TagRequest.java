package devsearch.projects.ws.ui.model.request;

public class TagRequest {

    private String tagId;

    private String name;

    private String publicKey;

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

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }
}
