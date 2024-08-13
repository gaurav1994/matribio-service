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

    
}
