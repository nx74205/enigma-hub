package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.DiscoverResponse;
import de.couchkiwi.enigmahub.model.AlexaDiscoverRequest;
import de.couchkiwi.enigmahub.model.AlexaDiscoverResponse;
import de.couchkiwi.enigmahub.model.AmazonRequest;
import de.couchkiwi.enigmahub.model.AmazonResponse;
import de.couchkiwi.enigmahub.ReceiverCommand;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RestService {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ReceiverCommand receiverCommand;

    @Autowired
    CredentialService credentialService;

    @RequestMapping(value = "/alexacmd", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AmazonResponse testRequest(@RequestBody AmazonRequest amazonRequest) {

        //log.debug(amazonRequest.toString());
        AmazonResponse ams = receiverCommand.getCommand(amazonRequest);
        return ams;

    }

    @RequestMapping(value = "/alexadiscover", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AlexaDiscoverResponse AlexaDiscover(@RequestBody AlexaDiscoverRequest alexaDiscoverRequest) {

        log.debug("Got Discover Namespace: " + alexaDiscoverRequest.getDirective().getHeader().getNamespace() +
                  " Name: " + alexaDiscoverRequest.getDirective().getHeader().getName());

        credentialService.getCredential(alexaDiscoverRequest.getDirective().getPayload().getScope().getToken());
        DiscoverResponse discoverResponse = new DiscoverResponse();

        return discoverResponse.discover(alexaDiscoverRequest);
        //AmazonResponse ams = receiverCommand.getCommand(amazonRequest);
  //      AmazonResponse ams = new AmazonResponse();
 //       return ams;

    }

}
