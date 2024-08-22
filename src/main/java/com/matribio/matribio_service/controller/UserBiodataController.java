package com.matribio.matribio_service.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matribio.matribio_service.dto.UserBiodataDto;
import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.service.PDFGenerator;
import com.matribio.matribio_service.service.UserBiodataService;

import java.io.ByteArrayInputStream;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userbiodata")
public class UserBiodataController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserBiodataService userBiodataService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createUserBiodataController(@RequestBody UserBiodataDto userBiodataDto){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        // PropertyMap<UserBiodata, UserBiodataDto> userBiodataPropertyMap = new PropertyMap<UserBiodata, UserBiodataDto>() {
        //     @Override
        //     protected void configure() {
        //         skip(destination.getPersonalDetails().getDateOfBirth());
        //         skip(destination.getPersonalDetails().getTimeOfBirth());
        //     }
        // };
        // modelMapper.addMappings(userBiodataPropertyMap);
        UserBiodata userBiodata = modelMapper.map(userBiodataDto, UserBiodata.class);

        Optional<Integer> optionUserBiodataId = userBiodataService.addUserBiodata(userBiodata);
        if (optionUserBiodataId.isEmpty()) {
            throw new RuntimeException("User Bio Data not saved.");
        }
        return ResponseEntity.ok().body(optionUserBiodataId.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUserBiodataController(@PathVariable int id){
        Optional<Boolean> optionUserBiodata = userBiodataService.deleteUserBiodata(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not saved.");
        }
        return ResponseEntity.ok().body(optionUserBiodata.get());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserBiodataDto> getUserBiodataByIdController(@PathVariable int id){
        Optional<UserBiodata> optionUserBiodata = userBiodataService.getSingleUserDtoById(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not fetched with ID :" + id);
        }
        UserBiodataDto userBiodataDto = modelMapper.map(optionUserBiodata.get(), UserBiodataDto.class);
        return ResponseEntity.ok().body(userBiodataDto);
    }

    @GetMapping("/generatebiodatapdf/{id}")
    public ResponseEntity<InputStreamResource> generateBiodataPdf(@PathVariable int id) {
        Optional<UserBiodata> optionUserBiodata = userBiodataService.getSingleUserDtoById(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not fetched with ID :" + id);
        }
        ByteArrayInputStream bis = PDFGenerator.bioDataPDFReport(optionUserBiodata.get());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    

    @GetMapping("/genPdf/{n}")
    public ResponseEntity<InputStreamResource> getMethodName(@PathVariable int n) {
        
        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(n);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    

}
