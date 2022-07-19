package br.com.cauequeiroz.forum.model.controller;

import br.com.cauequeiroz.forum.model.Post;
import br.com.cauequeiroz.forum.model.controller.dto.PostDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class PostController {

    @RequestMapping("/posts")
    public List<PostDTO> getAll() {
        Post post = new Post();
        post.setTitle("Lorem ipsum");
        post.setMessage("Lorem ipsum silor dot amet, dots maquet.");
        post.setCreatedAt(LocalDateTime.now());

        return PostDTO.fromList(Arrays.asList(post, post, post, post));
    }

}
