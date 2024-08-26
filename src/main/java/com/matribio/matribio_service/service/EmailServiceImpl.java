package com.matribio.matribio_service.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matribio.matribio_service.entity.UserBiodata;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") private String sender;
 
    public String sendMailWithAttachment(UserBiodata userBiodata, String email) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
 
        try {
            
            String downloadPath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/userbiodata/generatebiodatapdf/")
                                                                .path(userBiodata.getId().toString()).toUriString();
            String mailDescription = "Hi "+userBiodata.getPersonalDetails().getFirstName()+
                                    ", \n\nYou can download your biodata by click the following link. \n\n " + downloadPath ;
            
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(mailDescription);
            mimeMessageHelper.setSubject( "Download Biodata - Matribio Application");

            long instantTimeEpoch = Instant.now().getEpochSecond();
            String fileName = userBiodata.getPersonalDetails().getFirstName()+"_"+instantTimeEpoch+".pdf";
            ByteArrayInputStream bioDataPDFReport = PDFGenerator.bioDataPDFReport(userBiodata);
            byte[] out = bioDataPDFReport.readAllBytes();
            File file = new File(fileName);
            FileUtils.writeByteArrayToFile(file, out);
            mimeMessageHelper.addAttachment( file.getName(), file);

            javaMailSender.send(mimeMessage);
            FileUtils.delete(file);
            return "Mail sent Successfully";
        }catch (MessagingException e) {
            return "Error while sending mail!!!";
        }catch(IOException e) {
            e.printStackTrace();
            return "Error! 😞";
        }
    }
    
}
