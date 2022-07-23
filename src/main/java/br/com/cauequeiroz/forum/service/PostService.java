package br.com.cauequeiroz.forum.service;

import br.com.cauequeiroz.forum.dto.*;
import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.repository.CourseRepository;
import br.com.cauequeiroz.forum.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CourseRepository courseRepository;

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

    public PostDetailDTO getById(Long id) {
        return new PostDetailDTO(postRepository.getReferenceById(id));
    }

    @Transactional
    public ResponseEntity<PostDTO> create(PostRequestDTO postRequestDTO, UriComponentsBuilder uriBuilder) {
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setMessage(postRequestDTO.getMessage());
        post.setCourse(courseRepository.findByName(postRequestDTO.getCourseName()));

        postRepository.save(post);

        URI uri = uriBuilder
                .path("/posts/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PostDTO(post));
    }

    @Transactional
    public ResponseEntity<PostDetailDTO> update(Long id, PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = postRepository.getReferenceById(id);
        post.setTitle(postUpdateRequestDTO.getTitle());
        post.setMessage(postUpdateRequestDTO.getMessage());

        return ResponseEntity.ok(new PostDetailDTO(post));
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessageDTO("This ID do not exist in our database."));
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
