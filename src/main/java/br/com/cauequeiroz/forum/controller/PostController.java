package br.com.cauequeiroz.forum.controller;

import br.com.cauequeiroz.forum.controller.dto.PostDetailDTO;
import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.controller.dto.PostDTO;
import br.com.cauequeiroz.forum.controller.form.PostForm;
import br.com.cauequeiroz.forum.repository.CourseRepository;
import br.com.cauequeiroz.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public PostDetailDTO getPostById(@PathVariable Long id) {
        return new PostDetailDTO(postRepository.getReferenceById(id));
    }

}
