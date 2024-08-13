package com.matribio.matribio_service.dto;

public class UserBiodataDto {
    private Integer id;
    private PersonalDetailsDto personalDetails;

    private FamilyDetailsDto familyDetails;
    
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

        
}
