package com.sbproj.first.controller;

import com.sbproj.first.entity.Claim;
import com.sbproj.first.entity.Post;
import com.sbproj.first.repository.ClaimRepository;
import com.sbproj.first.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// Marks this class as a REST controller, meaning Spring will handle
// HTTP requests and responses here and automatically serialize return 
// values to JSON.
@RestController

// All endpoints in this controller will be prefixed with 
// /api/posts/{postId}/claims, where {postId} is a path variable 
// that will be passed to the handler methods.
@RequestMapping("/api/posts/{postId}/claims")

// Allows cross-origin requests (e.g. from the React frontend on a different port)
@CrossOrigin
public class ClaimController {
    
    // Repository for performing databse operations on Claim records
    private final ClaimRepository claimRepository;

    // Repository for performing database operations on Post records
    // (needed to verify the post exists and is of type "FOUND")
    private final PostRepository postRepository;

    // Constructor injection - Spring automatically provides these dependecies at startup
    public ClaimController(ClaimRepository claimRepository, PostRepository postRepository) {
        this.claimRepository = claimRepository;
        this.postRepository = postRepository;
    }

    // Handles POST /api/posts/{postId}/claims
    // Creates a new claim against a specific FOUND post

    // @PathVariable: Binds the method parameter postId to the value of the URI template variable
    // @RequestBody: Binds the method parameter claimRequest to the body of the HTTP request (get the claim data from the request body)
    @PostMapping

    // ResponseEntity represents entire HTTP response including status code, headers, and body.
    // <?> Java generic wildcard meaning "any type" 
    // Overall, ResponseEntity<?> can hold a response with any type of body (e.g. Claim, List, String, etc)
    public ResponseEntity<?> createClaim(@PathVariable Long postId, @RequestBody CreateClaimRequest claimRequest) {

        // Look up the post by its ID - returns an Optional (may or may not exist)
        // Can either contain a Post object (<Post>) or be empty (Optional)
        Optional<Post> optionalPost = postRepository.findById(postId);

        // If no post was found with that ID - returns a 404 Not Found
        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        // Unwrap the Optional to get the actual Post object
        Post post = optionalPost.get();

        // Business rule: claims can only be submitted on FOUND posts, not LOST posts
        // If the post type is anything other  than "FOUND", reject with 400 Bad Request
        if (!"FOUND".equalsIgnoreCase(post.getPostType())) {
            return ResponseEntity.badRequest().body("Claims can only be submitted for FOUND posts.");
        }

        // Build a new claim entity to persist to the database
        Claim claim = new Claim();
        
        // Associate this claim with the post it belongs to
        claim.setPost(post);

        // Copy fields from the incoming request body into the new Claim entity
        claim.setClaimerName(claimRequest.getClaimerName());
        claim.setClaimerEmail(claimRequest.getClaimerEmail());
        claim.setClaimMessage(claimRequest.getClaimMessage());

        // Details the claimer provides to prove the item is theirs
        claim.setIdentifyingDetails(claimRequest.getIdentifyingDetails());

        // All new claims start as PENDING - status can be updated later (e.g. APPROVED/REJECTED)
        claim.setClaimStatus("PENDING");

        // Timestamp the claim at the moment of creation
        claim.setCreatedAt(LocalDateTime.now());

        // Save the claim to the database and get back the saved version (with generated ID)
        Claim savedClaim = claimRepository.save(claim);

        // Return 201 Created with the saved claim as the response body
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
    }

    // Handles GET /api/posts/{postId}/claims
    // Returns all claims submitted for a specific post
    @GetMapping
    public ResponseEntity<?> getClaimsByPostId(@PathVariable Long postId) {

        // Verify the post exists before trying to fetch its claims
        Optional<Post> optionalPost = postRepository.findById(postId);

        // If the post doesn't exist. return 404 Not Found
        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Fetch all claims associated with this post ID
        // Returns an empty list [] if no claims exist - never null
        List<Claim> claims = claimRepository.findByPostId(postId);

        // Return 200 OK with the list of claims (or empty list)
        return ResponseEntity.ok(claims);
    }
}
