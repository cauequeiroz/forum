package br.com.cauequeiroz.forum.controller.dto;

import br.com.cauequeiroz.forum.model.Course;

public class CourseDTO {

    private String name;

    private String category;

    public CourseDTO(Course course) {
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
