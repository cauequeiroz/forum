package br.com.cauequeiroz.forum.model.repository;

import br.com.cauequeiroz.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCourseName(String courseName);
}
