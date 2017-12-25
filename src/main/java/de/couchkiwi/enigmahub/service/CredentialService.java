package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.AmazonCredentialResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class CredentialService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String  URI = "https://api.amazon.com/user/profile";

    private HashMap<String, AmazonCredentialResponse> credentialList;

    @Autowired
    public CredentialService() {
        credentialList = new HashMap<String, AmazonCredentialResponse>();
        log.debug("Hashmap for Credentials initialized!");
    }

    public AmazonCredentialResponse getCredential(String token) {

        AmazonCredentialResponse amazonCredentialResponse;

        if (credentialList.containsKey(token)) {

            amazonCredentialResponse = credentialList.get(token);
            log.debug("Found User " + amazonCredentialResponse.getName() + " with E-Mail " + amazonCredentialResponse.getEmail() + " from previous lookup!");

        } else {

            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("Authorization", "bearer " + token);
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<AmazonCredentialResponse> response = restTemplate.exchange(URI, HttpMethod.GET, entity, AmazonCredentialResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                amazonCredentialResponse = response.getBody();

                log.debug("Found User " + amazonCredentialResponse.getName() + " with E-Mail " + amazonCredentialResponse.getEmail());
                credentialList.put(token, amazonCredentialResponse);

            } else {
                amazonCredentialResponse = new AmazonCredentialResponse();
                amazonCredentialResponse.setValidCredential(false);

            }
        }

        return amazonCredentialResponse;

    }
}
