package br.com.cauequeiroz.forum.controller;

import br.com.cauequeiroz.forum.dto.PostDTO;
import br.com.cauequeiroz.forum.dto.PostDetailDTO;
import br.com.cauequeiroz.forum.dto.PostRequestDTO;
import br.com.cauequeiroz.forum.dto.PostUpdateRequestDTO;
import br.com.cauequeiroz.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PostDTO> getAll(String courseName) {
        return postService.getAll(courseName);
    }

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody @Valid PostRequestDTO postRequestDTO, UriComponentsBuilder uriBuilder) {
        return postService.create(postRequestDTO, uriBuilder);
    }

    @GetMapping("/{id}")
    public PostDetailDTO getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailDTO> update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequestDTO postUpdateRequestDTO) {
        return postService.update(id, postUpdateRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return postService.delete(id);
    }
}
