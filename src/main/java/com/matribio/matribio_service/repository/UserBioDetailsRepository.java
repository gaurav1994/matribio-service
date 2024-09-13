package com.matribio.matribio_service.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matribio.matribio_service.entity.UserBiodata;

@Repository
public interface UserBioDetailsRepository extends JpaRepository<UserBiodata, Integer> {

    List<UserBiodata> findAllByUsername(String loggedUser, Pageable  pageable);

}
