package com.sbproj.first.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for creating a new Claim.
 * Includes validation annotations to ensure required fields and proper formats.
 */

public class CreateClaimRequest {
    //  Name of the person making the claim
    // @NotBlank: Cant be null or empty
    // @Size: Limits character length to 100
    @NotBlank(message = "Claimer name is required")
    @Size(max = 100, message = "Claimer name must be at most 100 characters")
    private String claimerName;

    // Email address of the claimer.
    // @Email: Must be a valid email format.
    @NotBlank(message = "Claimer email is required")
    @Email(message = "Claimer email must be valid")
    @Size(max = 255, message = "Claimer email must be at most 255 characters")
    private String claimerEmail;

    // The message provided by the claimer explaining/verifying their claim
    // No validation annotations, this field is optional
    private String claimMessage;

    // Details provided by the claimer to prove the items is theirs
    @NotBlank(message = "Identifying details are required")
    private String identifyingDetails;

    // Default constructor
    public CreateClaimRequest() {
    }

    // All-args constructor
    public CreateClaimRequest(String claimerName, String claimerEmail, String claimMessage, String identifyingDetails) {
        this.claimerName = claimerName;
        this.claimerEmail = claimerEmail;
        this.claimMessage = claimMessage;
        this.identifyingDetails = identifyingDetails;
    }

    // claimerName getter
    public String getClaimerName() {
        return claimerName;
    }

    // claimerName setter
    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    // claimerEmail getter
    public String getClaimerEmail() {
        return claimerEmail;
    }

    // claimerEmail setter
    public void setClaimerEmail(String claimerEmail) {
        this.claimerEmail = claimerEmail;
    }

    // claimMessage getter
    public String getClaimMessage() {
        return claimMessage;
    }

    // claimmessage setter
    public void setClaimMessage(String claimMessage) {
        this.claimMessage = claimMessage;
    }

    // identifyingDetails getter
    public String getIdentifyingDetails() {
        return identifyingDetails;
    }

    // identifyingDetails setter
    public void setIdentifyingDetails(String identifyingDetails) {
        this.identifyingDetails = identifyingDetails;
    }
}