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

// Marks this class as a REST controller - Spring will handle HTTP requests here
// and automatically serialize return values to JSON.
@RestController

// All endpoints in this controller are prefixed with /api/posts
@RequestMapping("/api/posts")
public class PostController {

    // Repository for performing databse operations on Post records
    private final PostRepository postRepository;

    // Contructor injection - Spring automatically provides this dependency at startup
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Handles GET /api/posts
    // Returns every post in the databse as a JSON array
    @GetMapping
    public List<Post> getAllPosts() {

        // findAll() is provided by JpaRepository - no custom query needed
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
        post.setStatus("OPEN");
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);
        return ResponseEntity.ok(savedPost);
    }
}