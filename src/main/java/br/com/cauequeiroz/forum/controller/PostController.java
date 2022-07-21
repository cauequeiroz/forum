package br.com.cauequeiroz.forum.controller;

import br.com.cauequeiroz.forum.controller.dto.PostDetailDTO;
import br.com.cauequeiroz.forum.controller.form.PostUpdateForm;
import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.controller.dto.PostDTO;
import br.com.cauequeiroz.forum.controller.form.PostForm;
import br.com.cauequeiroz.forum.repository.CourseRepository;
import br.com.cauequeiroz.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<PostDTO> getAll(String courseName) {
        List<Post> posts;

        if (courseName == null) {
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findByCourseName(courseName);
        }

        return posts.stream()
                .map(PostDTO::new)
                .toList();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PostDTO> create(@RequestBody @Valid PostForm postForm, UriComponentsBuilder uriBuilder) {
        Post post = postForm.toPost(courseRepository);
        postRepository.save(post);

        URI uri = uriBuilder
                .path("/posts/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PostDTO(post));
    }

    @GetMapping("/{id}")
    public PostDetailDTO getById(@PathVariable Long id) {
        return new PostDetailDTO(postRepository.getReferenceById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PostDetailDTO> update(@PathVariable Long id, @RequestBody @Valid PostUpdateForm postUpdateForm) {
        Post post = postUpdateForm.update(id, postRepository);

        return ResponseEntity.ok(new PostDetailDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
