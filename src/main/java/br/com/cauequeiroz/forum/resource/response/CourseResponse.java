package br.com.cauequeiroz.forum.resource.response;

import br.com.cauequeiroz.forum.model.Course;

public class CourseResponse {

    private String name;

    private String category;

    public CourseResponse(Course course) {
        this.name = course.getName();
        this.category = course.getCategory();
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
