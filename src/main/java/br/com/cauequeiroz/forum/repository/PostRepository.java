package br.com.cauequeiroz.forum.repository;

import br.com.cauequeiroz.forum.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCourseName(String courseName, Pageable pagination);
}
