package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.AlexaDiscover;
import de.couchkiwi.enigmahub.model.AmazonRequest;
import de.couchkiwi.enigmahub.model.AmazonResponse;
import de.couchkiwi.enigmahub.ReceiverCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/alexacmd")
public class RestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReceiverCommand receiverCommand;

    @RequestMapping(value = "/alexacmd", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public AmazonResponse testRequest(@RequestBody AmazonRequest amazonRequest) {

        log.debug(amazonRequest.toString());
        AmazonResponse ams = receiverCommand.getCommand(amazonRequest);
        return ams;

    }

    @RequestMapping(value = "/alexadiscover", method=RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public void AlexaDiscover(@RequestBody AlexaDiscover alexaDiscover) {

        log.debug(alexaDiscover.toString());
        //AmazonResponse ams = receiverCommand.getCommand(amazonRequest);
        AmazonResponse ams = new AmazonResponse();
 //       return ams;

    }

}
