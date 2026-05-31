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

    // Repository for performing databse operations on Post records (provides CRUD methods)
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

    // Handles GET /api/posts/{id}
    // Returns a single post by its ID, or 404 if it isn't found
    @GetMapping("/{id}")

    // Optional<Post>: a container that may or may not contain a Post object
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        
        // Looks up the post by its ID
        Optional<Post> post = postRepository.findById(id);

        // post.map(ResponseEntity::ok): if post is present, wrap it in a 200 OK response
        // orElseGet(() -> ResponseEntity.notFound().build()): if post is absent, return 404 Not Found
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Handles POST /api/posts
    // Creates a new post from the provided request body
    @PostMapping

    // @Valid: Makes sure the data sent in the request follows the rules we set in CreatePostRequest (DTO for Post)
    // @RequestBody: Tells spring to take the data from the HTTP request and turn it into a CreatePostRequest object (DTO)
    public ResponseEntity<Post> createPost(@Valid @RequestBody CreatePostRequest request) {

        // Creates a new Post entity and set its fields from the request DTO
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setLocation(request.getLocation());

        // Set the post type (e.g. "LOST" or "FOUND"), co verting to uppercase for consistency
        post.setPostType(request.getPostType().toUpperCase());

        // Set the status to "OPEN" for all new posts.
        post.setStatus("OPEN");

        // Set the creationg timestamp to the current date and time
        post.setCreatedAt(LocalDateTime.now());

        // Save the post to the database. .save() returns the saved Post, including its generated ID
        Post savedPost = postRepository.save(post);

        // Returns a 200 OK response with the saved post as the response body.
        return ResponseEntity.ok(savedPost);
    }
}