package br.com.cauequeiroz.forum.model.controller;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.model.controller.dto.PostDTO;
import br.com.cauequeiroz.forum.model.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/posts")
    public List<PostDTO> getAll(String courseName) {
        List<Post> posts;

        if (courseName == null) {
            posts = postRepository.findAll();
        } else {
            posts = postRepository.findByCourseName(courseName);
        }

        return PostDTO.fromList(posts);
    }

}
