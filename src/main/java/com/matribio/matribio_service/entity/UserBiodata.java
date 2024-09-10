package com.matribio.matribio_service.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@DynamicUpdate
@Entity
@Table(name = "user_biodata_tbl")
public class UserBiodata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String profilePicture;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonalDetails personalDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private FamilyDetails familyDetails;

    private String userId;
    private String username;
    private String receiptId;
    
    public UserBiodata() {
    }

    public UserBiodata(Integer id, String profilePicture, PersonalDetails personalDetails, FamilyDetails familyDetails,
            String userId, String username, String receiptId) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.personalDetails = personalDetails;
        this.familyDetails = familyDetails;
        this.userId = userId;
        this.username = username;
        this.receiptId = receiptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public FamilyDetails getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(FamilyDetails familyDetails) {
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

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    // Implement Builder DSP

    public static class UserBiodataBuilder {
        private Integer id;
        private String profilePicture;
        private PersonalDetails personalDetails;
        private FamilyDetails familyDetails;
        private String userId;
        private String username;
        private String receiptId;

        public UserBiodataBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public UserBiodataBuilder profilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
            return this;
        }
        public UserBiodataBuilder personalDetails(PersonalDetails personalDetails) {
            this.personalDetails = personalDetails;
            return this;
        }
        public UserBiodataBuilder familyDetails(FamilyDetails familyDetails) {
            this.familyDetails = familyDetails;
            return this;
        }
        public UserBiodataBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
        public UserBiodataBuilder username(String username) {
            this.username = username;
            return this;
        }
        public UserBiodataBuilder receiptId(String receiptId) {
            this.receiptId = receiptId;
            return this;
        }

        public UserBiodata build() {
            return new UserBiodata(id, profilePicture, personalDetails, familyDetails, userId, username, receiptId);
        }
    }
    public static UserBiodataBuilder builder() {
        return new UserBiodataBuilder();
    }
}
