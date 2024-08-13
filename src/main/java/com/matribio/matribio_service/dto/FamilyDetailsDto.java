package com.matribio.matribio_service.dto;

import java.util.List;


public class FamilyDetailsDto {
    private Integer id;
    private String fatherName;
    private String motherName;

    private List<FieldAttributeDto> otherFamilyInformation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public List<FieldAttributeDto> getOtherFamilyInformation() {
        return otherFamilyInformation;
    }

    public void setOtherFamilyInformation(List<FieldAttributeDto> otherFamilyInformation) {
        this.otherFamilyInformation = otherFamilyInformation;
    }

    
}
