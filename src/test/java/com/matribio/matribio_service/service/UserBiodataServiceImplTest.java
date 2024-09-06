package com.matribio.matribio_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.matribio.matribio_service.entity.FamilyDetails;
import com.matribio.matribio_service.entity.PersonalDetails;
import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.repository.UserBioDetailsRepository;

@SpringBootTest
public class UserBiodataServiceImplTest {

    @Autowired
    UserBiodataService biodataService;

    @MockBean
    UserBioDetailsRepository bioDetailsRepository;

    @Test
    void testAddUserBiodataWithoutFileForSuccess() {
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setId(1);
        personalDetails.setFirstName("abc");
        personalDetails.setHighestQualification("BBA");
        personalDetails.setWork("CM");
        FamilyDetails familyDetails = new FamilyDetails();
        familyDetails.setId(10);
        familyDetails.setFatherName("rishi");
        familyDetails.setMotherName("hemlata");
        UserBiodata userBiodata = new UserBiodata();
        userBiodata.setId(123);
        userBiodata.setPersonalDetails(personalDetails);
        userBiodata.setFamilyDetails(familyDetails);

        when(bioDetailsRepository.save(userBiodata)).thenReturn(userBiodata);
        Optional<Integer> optionalUserBiodata = biodataService.addUserBiodata(userBiodata, null, null);
        assertTrue(optionalUserBiodata.isPresent());
        assertEquals(123, optionalUserBiodata.get());
    }

    // @Test
    // void testDeleteUserBiodata() {

    // }

    // @Test
    // void testGetSingleUserDtoById() {

    // }
}
