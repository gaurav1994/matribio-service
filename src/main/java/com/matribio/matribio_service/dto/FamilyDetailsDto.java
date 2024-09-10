package com.matribio.matribio_service.dto;

import java.util.List;


public class FamilyDetailsDto {
    private Integer id;
    private String fatherName;
    private String motherName;

    private List<FieldAttributeDto> otherFamilyInformation;

    public FamilyDetailsDto() {
    }

    public FamilyDetailsDto(Integer id, String fatherName, String motherName,
            List<FieldAttributeDto> otherFamilyInformation) {
        this.id = id;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.otherFamilyInformation = otherFamilyInformation;
    }

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

    public static class FamilyDetailsDtoBuilder {
        private Integer id;
        private String fatherName;
        private String motherName;

        private List<FieldAttributeDto> otherFamilyInformation;

        public FamilyDetailsDtoBuilder id(Integer id) {
            this.id=id;
            return this;
        }
        public FamilyDetailsDtoBuilder motherName(String motherName) {
            this.motherName=motherName;
            return this;
        }
        public FamilyDetailsDtoBuilder fatherName(String fatherName) {
            this.fatherName=fatherName;
            return this;
        }
        public FamilyDetailsDtoBuilder otherFamilyInformation(List<FieldAttributeDto> otherFamilyInformation) {
            this.otherFamilyInformation=otherFamilyInformation;
            return this;
        }
        public FamilyDetailsDto build() {
            return new FamilyDetailsDto(id, fatherName, motherName, otherFamilyInformation);
        }
    }
    public static FamilyDetailsDtoBuilder builder() {
        return new FamilyDetailsDtoBuilder();
    }
}
