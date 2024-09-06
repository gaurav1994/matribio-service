package com.matribio.matribio_service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

    
}
