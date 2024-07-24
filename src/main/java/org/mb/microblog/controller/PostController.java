package org.mb.microblog.controller;

import org.mb.microblog.dto.PostDto;
import org.mb.microblog.model.Post;
import org.mb.microblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable Long id) {
        PostDto postDto = this.postService.postFindById(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostDto> save(@PathVariable Long userId, @RequestBody Post post) {
        try {
            PostDto postCreatedDto = postService.postCreate(post, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(postCreatedDto);
        } catch (Exception e) {
            System.out.println("Erro:");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
