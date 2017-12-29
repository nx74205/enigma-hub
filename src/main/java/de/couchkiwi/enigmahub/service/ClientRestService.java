package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.enigma.EnigmaRequest;
import de.couchkiwi.enigmahub.model.enigma.EnigmaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClientRestService {

    @Autowired
    ReceiverSubscribeService rss;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private HashMap commandRequest = new HashMap<String, DeferredResult>();
    private static long TIMEOUT = 45000;

    @RequestMapping(value="/commands", method=RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public DeferredResult<EnigmaResponse> receiverRequest (@RequestBody EnigmaRequest enigmaRequest) {

        rss.subscribe(enigmaRequest);
        EnigmaResponse defaultEnigmaResponse = new EnigmaResponse
                (enigmaRequest.geteMailAddress(), enigmaRequest.getReceiverModell(),   "NO_COMAND", "", "");

        final DeferredResult<EnigmaResponse> result = new DeferredResult<EnigmaResponse>(TIMEOUT, defaultEnigmaResponse);

        this.commandRequest.put(enigmaRequest.geteMailAddress()+enigmaRequest.getReceiverModell(), result);

        result.onCompletion(new Runnable() {
            public void run() {
                commandRequest.remove(enigmaRequest.geteMailAddress()+enigmaRequest.getReceiverModell());
            }
        });

        result.onTimeout (new Runnable() {
            public void run() {
                log.debug("Request Timed Out. Sending NO_COMMAND message!");
                commandRequest.remove(enigmaRequest.geteMailAddress()+enigmaRequest.getReceiverModell());
            }
        });

        return result;
    }

    public boolean setResult(String eMailAddress, String receiverModell, String alexaCommand) {

        if (this.commandRequest.containsKey(eMailAddress+receiverModell)) {
            DeferredResult<EnigmaResponse> result = (DeferredResult<EnigmaResponse>) this.commandRequest.get(eMailAddress+receiverModell);
            result.setResult(new EnigmaResponse(eMailAddress, receiverModell,alexaCommand,"", ""));

            return true;

        } else {
            log.debug("No Active connection found for " + eMailAddress + " with Receiver " + receiverModell);
            return false;
        }

    }

}
