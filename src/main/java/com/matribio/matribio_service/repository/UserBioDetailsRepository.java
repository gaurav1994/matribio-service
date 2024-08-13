package com.matribio.matribio_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matribio.matribio_service.entity.UserBiodata;

@Repository
public interface UserBioDetailsRepository extends JpaRepository<UserBiodata, Integer> {

}
