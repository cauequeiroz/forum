package br.com.cauequeiroz.forum.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDate createdAt;

    private boolean isPostSolution = false;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User author;

    public Reply() {
    }

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
