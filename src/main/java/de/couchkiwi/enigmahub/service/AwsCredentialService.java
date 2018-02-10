package de.couchkiwi.enigmahub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/*
    This is the clientservice to translate the token got from AWS to a valid E-Mail Address.
 */
@Component
public class AwsCredentialService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String  URI = "https://api.amazon.com/user/profile";

    private HashMap<String, String> credentialList;

    @Autowired
    public AwsCredentialService() {
        credentialList = new HashMap<String, String>();
        log.debug("Hashmap for Credentials initialized!");
    }

    public void getCredential(String token) {

        String amazonCredentialResponse;

        if (credentialList.containsKey(token)) {

            amazonCredentialResponse = credentialList.get(token);
            log.debug("Found ID-Token " + amazonCredentialResponse + " from previous lookup!");

        } else {

            amazonCredentialResponse = token;

            log.debug("Found ID-Token " + amazonCredentialResponse);
            credentialList.put(token, amazonCredentialResponse);
        }


    }

    public String getEmailFromCredential(String token) {

        if (credentialList.containsKey(token)) {
            log.debug("Token found for E-Mail");
            return credentialList.get(token);
        } else {
            return null;
        }
    }
}
