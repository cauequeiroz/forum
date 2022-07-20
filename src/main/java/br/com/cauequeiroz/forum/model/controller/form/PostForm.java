package br.com.cauequeiroz.forum.model.controller.form;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.model.repository.CourseRepository;

public class PostForm {

    private String title;

    private String message;

    private String courseName;

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

    public Post toPost(CourseRepository courseRepository) {
        Post post = new Post();
        post.setTitle(title);
        post.setMessage(message);
        post.setCourse(courseRepository.findByName(courseName));

        return post;
    }
}
