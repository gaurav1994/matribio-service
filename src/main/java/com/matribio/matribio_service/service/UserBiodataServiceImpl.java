package com.matribio.matribio_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.repository.UserBioDetailsRepository;

@Service
public class UserBiodataServiceImpl implements UserBiodataService{

    @Autowired
    UserBioDetailsRepository userBioDetailsRepository;

    @Override
    public Optional<Integer> addUserBiodata(UserBiodata userBiodata) {
        UserBiodata savedUserBiodata = userBioDetailsRepository.save(userBiodata);
        Optional<Integer> optionalUserBiodataId = Optional.empty();
        if (savedUserBiodata!= null) {
            optionalUserBiodataId = Optional.of(savedUserBiodata.getId());
        }
        return optionalUserBiodataId;
    }

    @Override
    public Optional<Boolean> deleteUserBiodata(int id) {
        Boolean isSuccessDelete = false;
        try {
            Optional<UserBiodata> userFoundWithId = userBioDetailsRepository.findById(id);
            if (userFoundWithId.isPresent()) {
                userBioDetailsRepository.deleteById(id);
                isSuccessDelete = true;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Optional.of(isSuccessDelete);
    }

    @Override
    public Optional<UserBiodata> getSingleUserDtoById(int id) {
        return userBioDetailsRepository.findById(id);
    }

}
