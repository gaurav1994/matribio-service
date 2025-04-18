package com.matribio.matribio_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matribio.matribio_service.dto.SimpleMessage;
import com.matribio.matribio_service.dto.UserBiodataDto;
import com.matribio.matribio_service.entity.PaymentTransaction;
import com.matribio.matribio_service.entity.UserBiodata;
import com.matribio.matribio_service.service.PDFGenerator;
import com.matribio.matribio_service.service.PaymentTransactionService;
import com.matribio.matribio_service.service.UserBiodataService;

import java.io.ByteArrayInputStream;
import java.security.Principal;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/userbiodata")
public class UserBiodataController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserBiodataService userBiodataService;

    @Autowired
    PaymentTransactionService paymentTransactionService;

    @PostMapping("/create")
    public ResponseEntity<Integer> createUserBiodataController(
                                        @RequestPart("data") UserBiodataDto userBiodataDto, 
                                        @RequestPart(value = "file" , required = false) MultipartFile file,
                                        Principal principal ){
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
        Optional<Integer> optionUserBiodataId = userBiodataService.addUserBiodata(userBiodata, file, principal);
        if (optionUserBiodataId.isEmpty()) {
            throw new RuntimeException("User Bio Data not saved.");
        }
        return ResponseEntity.ok().body(optionUserBiodataId.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SimpleMessage> deleteUserBiodataController(@PathVariable int id){
        Optional<Boolean> optionUserBiodata = userBiodataService.deleteUserBiodata(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not saved.");
        }
        return ResponseEntity.ok().body(new SimpleMessage("biodata deleted confimed"));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserBiodataDto> getUserBiodataByIdController(@PathVariable int id){
        Optional<UserBiodata> optionUserBiodata = userBiodataService.getSingleUserDtoById(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not fetched with ID :" + id);
        }
        String receiptId = optionUserBiodata.get().getReceiptId();
        PaymentTransaction paymentbyReceiptId = paymentTransactionService.getPaymentbyReceiptId(receiptId);
        UserBiodataDto mapedUserBioDto = modelMapper.map(optionUserBiodata.get(), UserBiodataDto.class);
        if (paymentbyReceiptId != null) {
            mapedUserBioDto.setPaymentStatus(paymentbyReceiptId.getStatus());
        }
        UserBiodataDto userBiodataDto = modelMapper.map(mapedUserBioDto, UserBiodataDto.class);
        return ResponseEntity.ok().body(userBiodataDto);
    }

    @GetMapping("/find")
    public ResponseEntity<List<UserBiodataDto>> getUserBiodataByIdController(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            Principal principal ){
        Optional<List<UserBiodata>> optionalListUserBiodata = userBiodataService.getAllByUsername(principal, page, size);
        if (optionalListUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data List not fetched :");
        }
        List<UserBiodataDto> listUserBiodataDto = optionalListUserBiodata.get().stream().map(item -> {
                                                String receiptId = item.getReceiptId();
                                                PaymentTransaction paymentbyReceiptId = paymentTransactionService.getPaymentbyReceiptId(receiptId);
                                                UserBiodataDto mapedUserBioDto = modelMapper.map(item, UserBiodataDto.class);
                                                if (paymentbyReceiptId != null) {
                                                    mapedUserBioDto.setPaymentStatus(paymentbyReceiptId.getStatus());
                                                }
                                                return mapedUserBioDto;
                                                }).collect(Collectors.toList());
        return ResponseEntity.ok().body(listUserBiodataDto);
    }

    @GetMapping("/generatebiodatapdf/{id}")
    public ResponseEntity<InputStreamResource> generateBiodataPdf(@PathVariable int id) {
        Optional<UserBiodata> optionUserBiodata = userBiodataService.getSingleUserDtoById(id);
        if (optionUserBiodata.isEmpty()) {
            throw new RuntimeException("User Bio Data not fetched with ID :" + id);
        }
        ByteArrayInputStream bis = PDFGenerator.bioDataPDFReport(optionUserBiodata.get());
        String contentDispositionName = "inline; filename="+optionUserBiodata.get().getPersonalDetails().getFirstName()+"_"
                            + optionUserBiodata.get().getPersonalDetails().getPlaceOfBirth()+".pdf" ;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", contentDispositionName);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    // @PutMapping("update-biodata/{id}")
    // public String putMethodName(@PathVariable Integer id, @RequestBody UserBiodata biodata) {
 
    //     userBiodataService.updateUserBiodata(id, biodata);

    //     return "Update success";
    // }

}
