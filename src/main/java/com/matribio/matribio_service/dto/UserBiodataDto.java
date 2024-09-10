package com.matribio.matribio_service.dto;

public class UserBiodataDto {
    private Integer id;
    private String profilePicture;
    private PersonalDetailsDto personalDetails;

    private FamilyDetailsDto familyDetails;

    private String userId;
    private String username;
    private String paymentStatus;
    
    public UserBiodataDto() {
    }

    public UserBiodataDto(Integer id, String profilePicture, PersonalDetailsDto personalDetails,
            FamilyDetailsDto familyDetails, String userId, String username, String paymentStatus) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.personalDetails = personalDetails;
        this.familyDetails = familyDetails;
        this.userId = userId;
        this.username = username;
        this.paymentStatus = paymentStatus;
    }

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

    // Implement Builder DSP

    public static class UserBiodataDtoBuilder {
        private Integer id;
        private String profilePicture;
        private PersonalDetailsDto personalDetails;
        private FamilyDetailsDto familyDetails;
        private String userId;
        private String username;
        private String paymentStatus;

        public UserBiodataDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public UserBiodataDtoBuilder profilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }
        public UserBiodataDtoBuilder PersonalDetailsDto(PersonalDetailsDto personalDetails) {
            this.personalDetails = personalDetails;
            return this;
        }
        public UserBiodataDtoBuilder FamilyDetailsDto(FamilyDetailsDto familyDetails) {
            this.familyDetails = familyDetails;
            return this;
        }
        public UserBiodataDtoBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
        public UserBiodataDtoBuilder username(String username) {
            this.username = username;
            return this;
        }
        public UserBiodataDtoBuilder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public UserBiodataDto build() {
            return new UserBiodataDto(id, profilePicture, personalDetails, familyDetails, userId, username, paymentStatus);
        }
    }
    public static UserBiodataDtoBuilder builder() {
        return new UserBiodataDtoBuilder();
    }
    
}
