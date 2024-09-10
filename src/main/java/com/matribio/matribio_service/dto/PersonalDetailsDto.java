package com.matribio.matribio_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PersonalDetailsDto {
    private Integer id;
    private String firstName;
    private String placeOfBirth;
    private LocalDate dateOfBirth;
    private LocalTime timeOfBirth;
    // private String height;
    private String highestQualification;
    private String work;

    public PersonalDetailsDto() {
    }

    public PersonalDetailsDto(Integer id, String firstName, String placeOfBirth, LocalDate dateOfBirth,
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

    public static class PersonalDetailsDtoBuilder {
        private Integer id;
        private String firstName;
        private String placeOfBirth;
        private LocalDate dateOfBirth;
        private LocalTime timeOfBirth;
        // private String height;
        private String highestQualification;
        private String work;

        public PersonalDetailsDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public PersonalDetailsDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public PersonalDetailsDtoBuilder placeOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
            return this;
        }
        public PersonalDetailsDtoBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public PersonalDetailsDtoBuilder timeOfBirth(LocalTime timeOfBirth) {
            this.timeOfBirth = timeOfBirth;
            return this;
        }
        public PersonalDetailsDtoBuilder highestQualification(String highestQualification) {
            this.highestQualification = highestQualification;
            return this;
        }
        public PersonalDetailsDtoBuilder work(String work) {
            this.work = work;
            return this;
        }
        public PersonalDetailsDto build() {
            return new PersonalDetailsDto(id, firstName, placeOfBirth, dateOfBirth, timeOfBirth, highestQualification, work);
        }
    }
    public static PersonalDetailsDtoBuilder builder() {
        return new PersonalDetailsDtoBuilder();
    }

}
