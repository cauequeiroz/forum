package br.com.cauequeiroz.forum.controller.dto;

import br.com.cauequeiroz.forum.model.Post;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostDTO {

    private Long id;

    private String title;

    private String message;

    private String createdAt;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.createdAt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(post.getCreatedAt());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
