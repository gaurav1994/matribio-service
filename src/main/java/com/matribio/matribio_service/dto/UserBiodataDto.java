package com.matribio.matribio_service.dto;

public class UserBiodataDto {
    private Integer id;
    private String profilePicture;
    private PersonalDetailsDto personalDetails;

    private FamilyDetailsDto familyDetails;

    private String userId;
    private String username;
    private String paymentStatus;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonalDetailsDto getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetailsDto personalDetails) {
        this.personalDetails = personalDetails;
    }

    public FamilyDetailsDto getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(FamilyDetailsDto familyDetails) {
        this.familyDetails = familyDetails;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
}
