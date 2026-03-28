package com.sbproj.first.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "claimer_name", nullable = false, length = 100)
    private String claimerName;

    @Column(name = "claimer_email", nullable = false, length = 255)
    private String claimerEmail;

    @Column(name = "claim_message", columnDefinition = "TEXT")
    private String claimMessage;

    @Column(name = "identifying_details", nullable = false, columnDefinition = "TEXT")
    private String identifyingDetails;

    @Column(name = "claim_status", nullable = false, length = 20)
    private String claimStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Claim() {
    }

    public Claim(Long id, Post post, String claimerName, String claimerEmail, String claimMessage, 
                    String identifyingDetails, String claimStatus, LocalDateTime createdAt) {
        this.id = id;
        this.post = post;
        this.claimerName = claimerName;
        this.claimerEmail = claimerEmail;
        this.claimMessage = claimMessage;
        this.identifyingDetails = identifyingDetails;
        this.claimStatus = claimStatus;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    public String getClaimerEmail() {
        return claimerEmail;
    }

    public void setClaimerEmail(String claimerEmail) {
        this.claimerEmail = claimerEmail;
    }

    public String getClaimMessage() {
        return claimMessage;
    }

    public void setClaimMessage(String claimMessage) {
        this.claimMessage = claimMessage;
    }

    public String getIdentifyingDetails() {
        return identifyingDetails;
    }

    public void setIdentifyingDetails(String identifyingDetails) {
        this.identifyingDetails = identifyingDetails;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}