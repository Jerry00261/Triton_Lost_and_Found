package com.sbproj.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for creating a new Post.
 * Includes validation annotations to ensure required fields and proper formats.
 */

public class CreatePostRequest {

    // Title of the post.
    // @NotBlank: Field cant be empty or null
    // @Size: limited to a maximum length of 120 characters.
    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title must be at most 120 characters")
    private String title;

    // Description of the post.
    @NotBlank(message = "Description is required")
    private String description;

    // Location related to the post 
    @Size(max = 120, message = "Location must be at most 120 characters")
    private String location;

    // Type of post (either LOST or FOUND)
    @NotBlank(message = "Post type is required")
    private String postType; 

    // No arg constructor
    public CreatePostRequest() {}

    // All arg constructor
    public CreatePostRequest(String title, String description, String location, String postType, String privateIdentifyingDetails) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.postType = postType;
    }

    // title getter
    public String getTitle() {
        return title;
    }

    // title setter
    public void setTitle(String title) {
        this.title = title;
    }

    // description getter
    public String getDescription() {
        return description;
    }

    // description setter
    public void setDescription(String description) {
        this.description = description;
    }

    // location getter
    public String getLocation() {
        return location;
    }

    // location setter
    public void setLocation(String location) {
        this.location = location;
    }

    // posttype getter
    public String getPostType() {
        return postType;
    }

    // posttype setter
    public void setPostType(String postType) {
        this.postType = postType;
    }
}
