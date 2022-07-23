package br.com.cauequeiroz.forum.resource.request;

import javax.validation.constraints.NotEmpty;

public class PostUpdateRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
