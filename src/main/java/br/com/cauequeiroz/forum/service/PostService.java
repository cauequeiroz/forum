package br.com.cauequeiroz.forum.service;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.repository.CourseRepository;
import br.com.cauequeiroz.forum.repository.PostRepository;
import br.com.cauequeiroz.forum.repository.UserRepository;
import br.com.cauequeiroz.forum.resource.request.PostRequest;
import br.com.cauequeiroz.forum.resource.request.PostUpdateRequest;
import br.com.cauequeiroz.forum.resource.response.ErrorMessageResponse;
import br.com.cauequeiroz.forum.resource.response.PostResponse;
import br.com.cauequeiroz.forum.resource.response.PostDetailResponse;
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

    @Autowired
    private UserRepository userRepository;

    public List<PostResponse> getAll(String courseName) {
        List<Post> posts;

        if (courseName == null) {
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findByCourseName(courseName);
        }

        return posts.stream()
                .map(PostResponse::new)
                .toList();
    }

    public PostDetailResponse getById(Long id) {
        return new PostDetailResponse(postRepository.getReferenceById(id));
    }

    @Transactional
    public ResponseEntity<PostResponse> create(PostRequest postRequest, UriComponentsBuilder uriBuilder) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setMessage(postRequest.getMessage());
        post.setCourse(courseRepository.findByName(postRequest.getCourseName()));
        post.setAuthor(userRepository.findByName(postRequest.getAuthorName()));

        postRepository.save(post);

        URI uri = uriBuilder
                .path("/posts/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PostResponse(post));
    }

    @Transactional
    public ResponseEntity<PostDetailResponse> update(Long id, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.getReferenceById(id);
        post.setTitle(postUpdateRequest.getTitle());
        post.setMessage(postUpdateRequest.getMessage());

        return ResponseEntity.ok(new PostDetailResponse(post));
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorMessageResponse("This ID do not exist in our database."));
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
