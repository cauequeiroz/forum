package br.com.cauequeiroz.forum.dto;

import javax.validation.constraints.NotEmpty;

public class PostUpdateRequestDTO {

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
