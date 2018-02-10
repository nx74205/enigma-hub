package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.enigma.EnigmaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class ReceiverSubscribeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private NavigableMap<String, EnigmaRequest> alexaUserlist;

    @Autowired
    ClientRestService crs;

    @Autowired
    public ReceiverSubscribeService() {

        this.alexaUserlist = new TreeMap<>();

    }

    public SortedMap<String, EnigmaRequest> getAlexaUserlist() {
        return alexaUserlist;
    }

    public boolean subscribe (EnigmaRequest s) {


        if(!alexaUserlist.containsKey(s.getIdToken()+s.getReceiverModell())) {
            EnigmaRequest subscribedEntry = s;
            alexaUserlist.put(s.getIdToken()+s.getReceiverModell(), subscribedEntry);
            log.debug("User " + s.getIdToken() + " with Receiver " + s.getReceiverModell() + " subscribed to Service!");
        } else {
            log.debug("User " + s.getIdToken() + " with Receiver " + s.getReceiverModell() + " already subscribed!");
        }
        return true;
    }

    public boolean setResult(String idToken, String receiverModell, String alexaCommand) {

        return crs.setResult(idToken, receiverModell, alexaCommand);

    }
}
