package com.sbproj.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreatePostRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 120, message = "Title must be at most 120 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @Size(max = 120, message = "Location must be at most 120 characters")
    private String location;

    @NotBlank(message = "Post type is required")
    private String postType; 

    private String privateIdentifyingDetails;
    public CreatePostRequest() {}

    public CreatePostRequest(String title, String description, String location, String postType, String privateIdentifyingDetails) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.postType = postType;
        this.privateIdentifyingDetails = privateIdentifyingDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPrivateIdentifyingDetails() {
        return privateIdentifyingDetails;
    }

    public void setPrivateIdentifyingDetails(String privateIdentifyingDetails) {
        this.privateIdentifyingDetails = privateIdentifyingDetails;
    }

}
