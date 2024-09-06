package com.matribio.matribio_service.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.matribio.matribio_service.entity.User;
import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.repository.UserBioDetailsRepository;

@Service
public class UserBiodataServiceImpl implements UserBiodataService{

    @Autowired
    UserBioDetailsRepository userBioDetailsRepository;

    private Path root = Paths.get("uploads");

    @Override
    public Optional<Integer> addUserBiodata(UserBiodata userBiodata, MultipartFile file, Principal principal) {

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            User loggedUser = (User) usernamePasswordAuthenticationToken.getPrincipal();
            String fullPath = null;
            if (file != null) {    
                long instantTimeEpoch = Instant.now().getEpochSecond();
                String originalFilename = file.getOriginalFilename();
                if (originalFilename != null){
                    String actualFileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        
                    if (root.getParent() == null) {
                        root = Files.createDirectories(root);
                    }
                    fullPath =  root.toAbsolutePath()+ "/" + actualFileName + "_" + instantTimeEpoch + originalFilename.substring(originalFilename.lastIndexOf("."));
                    file.transferTo(new File(fullPath));
                }
            }
            userBiodata.setProfilePicture(fullPath);
            userBiodata.setUserId(loggedUser.getUserId());
            userBiodata.setUsername(loggedUser.getUsername());
            UserBiodata savedUserBiodata = userBioDetailsRepository.save(userBiodata);
            Optional<Integer> optionalUserBiodataId = Optional.empty();
            if (savedUserBiodata!= null) {
                optionalUserBiodataId = Optional.of(savedUserBiodata.getId());
            }
            return optionalUserBiodataId;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
