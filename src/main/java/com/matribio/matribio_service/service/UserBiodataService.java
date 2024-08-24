package com.matribio.matribio_service.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.matribio.matribio_service.entity.UserBiodata;

public interface UserBiodataService {
    Optional<Integer> addUserBiodata(UserBiodata userBiodata, MultipartFile file);
    Optional<Boolean> deleteUserBiodata(int id);
    Optional<UserBiodata> getSingleUserDtoById(int id);
}
