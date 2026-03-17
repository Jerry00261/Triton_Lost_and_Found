package com.sbproj.first.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(length = 120)
    private String location

    @Column(name = "post_type", nullable = false, length = 20) //! Originally was 10 but changed to 20, ask chat if okay
    private String postType; //? Why is the name not matching the name in the table

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "private_identifying_details", columnDefinition = "TEXT")
    private String privateIdentifyingDetails;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Post(){}

    public Post(Long id, String title, String description, String location, 
                String postType, String status, String privateIdentifyingDetails, 
                LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.postType = postType;
        this.status = status;
        this.privateIdentifyingDetails = privateIdentifyingDetails;
        this.createdAt = createdAt;
    }

    
}