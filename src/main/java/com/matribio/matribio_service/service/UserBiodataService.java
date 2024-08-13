package com.matribio.matribio_service.service;

import java.util.Optional;
import com.matribio.matribio_service.entity.UserBiodata;

public interface UserBiodataService {
    Optional<Integer> addUserBiodata(UserBiodata userBiodata);
    Optional<Boolean> deleteUserBiodata(int id);
    Optional<UserBiodata> getSingleUserDtoById(int id);
}
