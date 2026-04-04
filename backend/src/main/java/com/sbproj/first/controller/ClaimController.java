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


@RestController
@RequestMapping("/api/posts/{postId}/claims")
@CrossOrigin
public class ClaimController {
    
    private final ClaimRepository claimRepository;
    private final PostRepository postRepository;

    public ClaimController(ClaimRepository claimRepository, PostRepository postRepository) {
        this.claimRepository = claimRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<?> createClaim(@PathVariable Long postId, @RequestBody Claim claimRequest) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Post post = optionalPost.get();

        if (!"FOUND".equalsIgnoreCase(post.getPostType())) {
            return ResponseEntity.badRequest().body("Claims can only be submitted for FOUND posts.");
        }

        Claim claim = new Claim();
        claim.setPost(post);
        claim.setClaimerName(claimRequest.getClaimerName());
        claim.setClaimerEmail(claimRequest.getClaimerEmail());
        claim.setClaimMessage(claimRequest.getClaimMessage());
        claim.setIdentifyingDetails(claimRequest.getIdentifyingDetails());
        claim.setClaimStatus("PENDING");
        claim.setCreatedAt(LocalDateTime.now());

        Claim savedClaim = claimRepository.save(claim);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
    }

    @GetMapping
    public ResponseEntity<?> getClaimsByPostId(@PathVariable Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if(optionalPost.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Claim> claims = claimRepository.findById(postId);
        return ResponseEntity.ok(claims);
    }
}
