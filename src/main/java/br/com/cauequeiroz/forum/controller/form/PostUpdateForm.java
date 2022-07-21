package br.com.cauequeiroz.forum.controller.form;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.repository.PostRepository;

import javax.validation.constraints.NotEmpty;

public class PostUpdateForm {

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

    public Post update(Long id, PostRepository postRepository) {
        Post post = postRepository.getReferenceById(id);
        post.setTitle(title);
        post.setMessage(message);

        return post;
    }
}
