package de.couchkiwi.enigmahub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.couchkiwi.enigmahub.model.enigma.EnigmaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class CallEnigmaService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public boolean sendCommand(String url, String api, String parameter) {


        RestTemplate restTemplate = new RestTemplate();

        EnigmaResponse enigmaResponse = null;
        try {
            log.debug(url+api+parameter);
            enigmaResponse = new ObjectMapper().readValue(restTemplate.getForObject(url+api+parameter, String.class), EnigmaResponse.class);
        } catch (IOException e) {

            throw new RuntimeException();
        }

        return enigmaResponse.isResult();
    }
}

