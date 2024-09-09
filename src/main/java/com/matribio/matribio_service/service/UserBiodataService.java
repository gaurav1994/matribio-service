package com.matribio.matribio_service.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.matribio.matribio_service.entity.UserBiodata;

public interface UserBiodataService {
    Optional<Integer> addUserBiodata(UserBiodata userBiodata, MultipartFile file, Principal principal);
    Optional<Boolean> deleteUserBiodata(int id);
    Optional<UserBiodata> getSingleUserDtoById(int id);
    Optional<UserBiodata> updateUserBiodata(int id , UserBiodata userBiodata);
    Optional<List<UserBiodata>> getAllByUsername(Principal principal);
}
