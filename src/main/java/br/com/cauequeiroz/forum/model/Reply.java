package br.com.cauequeiroz.forum.model;

import java.time.LocalDate;

public class Reply {

    private Long id;

    private String message;

    private LocalDate createdAt;

    private boolean isPostSolution = false;

    private Post post;

    private User author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isPostSolution() {
        return isPostSolution;
    }

    public void setPostSolution(boolean postSolution) {
        isPostSolution = postSolution;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
