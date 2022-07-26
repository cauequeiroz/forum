package br.com.cauequeiroz.forum.controller;

import br.com.cauequeiroz.forum.resource.response.PostResponse;
import br.com.cauequeiroz.forum.resource.response.PostDetailResponse;
import br.com.cauequeiroz.forum.resource.request.PostRequest;
import br.com.cauequeiroz.forum.resource.request.PostUpdateRequest;
import br.com.cauequeiroz.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @Cacheable(value = "posts/getAll")
    public List<PostResponse> getAll(String courseName, Pageable pagination) {
        return postService.getAll(courseName, pagination);
    }

    @PostMapping
    @CacheEvict(value = "posts/getAll", allEntries = true)
    public ResponseEntity<PostResponse> create(@RequestBody @Valid PostRequest postRequest, UriComponentsBuilder uriBuilder) {
        return postService.create(postRequest, uriBuilder);
    }

    @GetMapping("/{id}")
    public PostDetailResponse getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "posts/getAll", allEntries = true)
    public ResponseEntity<PostDetailResponse> update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        return postService.update(id, postUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "posts/getAll", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return postService.delete(id);
    }
}
