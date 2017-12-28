package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.DiscoverResponse;
import de.couchkiwi.enigmahub.model.AlexaDiscoverRequest;
import de.couchkiwi.enigmahub.model.AlexaDiscoverResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    This class contains all required Enpoints for the Amazon AWS Service
 */
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AlexaRestService {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AwsCredentialService acs;

    @Autowired
    ReceiverSubscribeService rss;

    @RequestMapping(value = "/alexadiscover", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AlexaDiscoverResponse AlexaDiscover(@RequestBody AlexaDiscoverRequest alexaDiscoverRequest) {

        log.debug("Got Discover Namespace: " + alexaDiscoverRequest.getDirective().getHeader().getNamespace() +
                  " Name: " + alexaDiscoverRequest.getDirective().getHeader().getName());

        acs.getCredential(alexaDiscoverRequest.getDirective().getPayload().getScope().getToken());
        DiscoverResponse discoverResponse = new DiscoverResponse();

        return discoverResponse.discover(alexaDiscoverRequest, acs, rss);

    }

}
