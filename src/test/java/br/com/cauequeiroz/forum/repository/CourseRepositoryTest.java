package br.com.cauequeiroz.forum.repository;

import br.com.cauequeiroz.forum.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void shouldReturnCourseWhenFindByName() {
        String courseName = "Javascript";
        Course course = courseRepository.findByName(courseName);

        assertNotNull(course);
        assertEquals(courseName, course.getName());
    }

    @Test
    void shouldNotReturnCourseWhenCourseDoNotExists() {
        String courseName = "C#";
        Course course = courseRepository.findByName(courseName);

        assertNull(course);
    }
}