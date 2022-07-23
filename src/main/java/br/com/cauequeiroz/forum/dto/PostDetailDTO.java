package br.com.cauequeiroz.forum.dto;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.model.PostStatus;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostDetailDTO {

    private Long id;

    private String title;

    private String message;

    private String createdAt;

    private PostStatus status;

    private String authorName;

    private CourseDTO course;

    private List<ReplyDTO> replies;

    public PostDetailDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.createdAt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(post.getCreatedAt());
        this.status = post.getStatus();
        this.authorName = post.getAuthor().getName();
        this.course = new CourseDTO(post.getCourse());

        List<ReplyDTO> replies = post.getReplies().stream().map(ReplyDTO::new).toList();
        this.replies = replies;
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

    public PostStatus getStatus() {
        return status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public List<ReplyDTO> getReplies() {
        return replies;
    }
}
