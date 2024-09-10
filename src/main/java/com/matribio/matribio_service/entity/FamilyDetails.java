package com.matribio.matribio_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "family_details_tbl")
public class FamilyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fatherName;
    private String motherName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FieldAttribute> otherFamilyInformation;

    public FamilyDetails() {
    }

    public FamilyDetails(Integer id, String fatherName, String motherName,
            List<FieldAttribute> otherFamilyInformation) {
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

    public List<FieldAttribute> getOtherFamilyInformation() {
        return otherFamilyInformation;
    }

    public void setOtherFamilyInformation(List<FieldAttribute> otherFamilyInformation) {
        this.otherFamilyInformation = otherFamilyInformation;
    }

    public static class FamilyDetailsBuilder {
        private Integer id;
        private String fatherName;
        private String motherName;
        private List<FieldAttribute> otherFamilyInformation;

        public FamilyDetailsBuilder id(Integer id) {
            this.id=id;
            return this;
        }
        public FamilyDetailsBuilder motherName(String motherName) {
            this.motherName=motherName;
            return this;
        }
        public FamilyDetailsBuilder fatherName(String fatherName) {
            this.fatherName=fatherName;
            return this;
        }
        public FamilyDetailsBuilder otherFamilyInformation(List<FieldAttribute> otherFamilyInformation) {
            this.otherFamilyInformation=otherFamilyInformation;
            return this;
        }
        public FamilyDetails build() {
            return new FamilyDetails(id, fatherName, motherName, otherFamilyInformation);
        }
    }
    public static FamilyDetailsBuilder builder() {
        return new FamilyDetailsBuilder();
    }
}
