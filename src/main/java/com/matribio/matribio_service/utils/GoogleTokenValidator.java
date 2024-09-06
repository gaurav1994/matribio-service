package com.matribio.matribio_service.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Component
public class GoogleTokenValidator {
    private static final String CLIENT_ID = "561951454537-cj39cjb87n87efh8i3giv8jrm600ah4i.apps.googleusercontent.com";
    private static final String ISSUER = "https://accounts.google.com";

    public GoogleIdToken validateToken(String token) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .setIssuer(ISSUER)
                .build();

        try {
            return verifier.verify(token);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
