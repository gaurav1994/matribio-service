package com.matribio.matribio_service.service;

import com.matribio.matribio_service.entity.UserBiodata;

public interface EmailService {

    String sendMailWithAttachment(UserBiodata userBiodata, String email);
}
