package br.com.cauequeiroz.forum.model.controller.dto;

import br.com.cauequeiroz.forum.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PostDTO {

    private String title;

    private String message;

    private String createdAt;

    public PostDTO(Post post) {
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.createdAt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(post.getCreatedAt());
    }

    public static List<PostDTO> fromList(List<Post> posts) {
        return posts.stream()
                .map(PostDTO::new)
                .toList();
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
