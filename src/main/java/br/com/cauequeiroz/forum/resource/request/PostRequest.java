package br.com.cauequeiroz.forum.resource.request;

import javax.validation.constraints.NotEmpty;

public class PostRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    @NotEmpty
    private String courseName;

    @NotEmpty
    private String authorName;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
