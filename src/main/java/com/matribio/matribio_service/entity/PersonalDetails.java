package com.matribio.matribio_service.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "user_personal_details_tbl")
public class PersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String placeOfBirth;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;
    @Temporal(TemporalType.TIME)
    private LocalTime timeOfBirth;
    // private String height;
    private String highestQualification;
    private String work;

    public PersonalDetails() {
    }

    public PersonalDetails(Integer id, String firstName, String placeOfBirth, LocalDate dateOfBirth,
            LocalTime timeOfBirth, String highestQualification, String work) {
        this.id = id;
        this.firstName = firstName;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.timeOfBirth = timeOfBirth;
        this.highestQualification = highestQualification;
        this.work = work;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalTime getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(LocalTime timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    // Implement Builder DSP

    public static class PersonalDetailsBuilder {
        private Integer id;
        private String firstName;
        private String placeOfBirth;
        private LocalDate dateOfBirth;
        private LocalTime timeOfBirth;
        // private String height;
        private String highestQualification;
        private String work;

        public PersonalDetailsBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public PersonalDetailsBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public PersonalDetailsBuilder placeOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
            return this;
        }
        public PersonalDetailsBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public PersonalDetailsBuilder timeOfBirth(LocalTime timeOfBirth) {
            this.timeOfBirth = timeOfBirth;
            return this;
        }
        public PersonalDetailsBuilder highestQualification(String highestQualification) {
            this.highestQualification = highestQualification;
            return this;
        }
        public PersonalDetailsBuilder work(String work) {
            this.work = work;
            return this;
        }
        public PersonalDetails build() {
            return new PersonalDetails(id, firstName, placeOfBirth, dateOfBirth, timeOfBirth, highestQualification, work);
        }
    }
    public static PersonalDetailsBuilder builder() {
        return new PersonalDetailsBuilder();
    }
}
