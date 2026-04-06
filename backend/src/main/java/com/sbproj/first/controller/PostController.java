package com.sbproj.first.controller;

import com.sbproj.first.dto.CreatePostRequest;
import com.sbproj.first.entity.Post;
import com.sbproj.first.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody CreatePostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setLocation(request.getLocation());
        post.setPostType(request.getPostType().toUpperCase());
        post.setPrivateIdentifyingDetails(request.getPrivateIdentifyingDetails());
        post.setStatus("OPEN");
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }
}