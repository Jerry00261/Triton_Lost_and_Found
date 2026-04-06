package com.sbproj.first.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateClaimRequest {

    @NotBlank(message = "Claimer name is required")
    @Size(max = 100, message = "Claimer name must be at most 100 characters")
    private String claimerName;

    @NotBlank(message = "Claimer email is required")
    @Email(message = "Claimer email must be valid")
    @Size(max = 255, message = "Claimer email must be at most 255 characters")
    private String claimerEmail;

    private String claimMessage;

    @NotBlank(message = "Identifying details are required")
    private String identifyingDetails;

    public CreateClaimRequest() {
    }

    public CreateClaimRequest(String claimerName, String claimerEmail, String claimMessage, String identifyingDetails) {
        this.claimerName = claimerName;
        this.claimerEmail = claimerEmail;
        this.claimMessage = claimMessage;
        this.identifyingDetails = identifyingDetails;
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
}