package de.couchkiwi.enigmahub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.couchkiwi.enigmahub.DiscoverResponse;
import de.couchkiwi.enigmahub.PowerResponse;
import de.couchkiwi.enigmahub.model.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/*
    This class contains all required Enpoints for the Amazon AWS Service
 */
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AlexaRestService {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AwsCredentialService acs;

    @Autowired
    ReceiverSubscribeService rss;

    @Autowired
    ClientRestService crs;

    @RequestMapping(value = "/alexadiscover", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AlexaDiscoverResponse AlexaDiscover(@RequestBody AlexaDiscoverRequest alexaDiscoverRequest) {

        log.debug("Got Discover Namespace: " + alexaDiscoverRequest.getDirective().getHeader().getNamespace() +
                  " Name: " + alexaDiscoverRequest.getDirective().getHeader().getName());

        acs.getCredential(alexaDiscoverRequest.getDirective().getPayload().getScope().getToken());
        DiscoverResponse discoverResponse = new DiscoverResponse();

        return discoverResponse.discover(alexaDiscoverRequest, acs, rss);

    }

    @RequestMapping(value = "/alexadirective", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AlexaPowerResponse AlexaSendPower(@RequestBody AlexaPowerRequest alexaPowerRequest) {

        acs.getCredential(alexaPowerRequest.getDirective().getEndpoint().getScope().getToken());
        PowerResponse powerResponse = new PowerResponse();

        log.debug(alexaPowerRequest.toString());

        return powerResponse.respond(alexaPowerRequest, acs, rss);

    }

    @RequestMapping(value = "/auth", method= RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public DumbAuth auth(@RequestParam(value="code", required=false) String code) {


        DumbAuth dumbAuth = new DumbAuth();

        dumbAuth.setAccess_token(code);
        dumbAuth.setRefresh_token(code);

        return dumbAuth;
    }

}
