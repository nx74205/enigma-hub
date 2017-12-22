package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.enigma.EnigmaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

@Service
public class AlexaCommandService {

    @Value(("${alexa_command_file}"))
    private String fileName;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private HashMap<String, EnigmaRequest> commandline;

    public AlexaCommandService() {
    }

    @PostConstruct
    public void init() throws ParseException, IOException {

        log.info(String.format(fileName));

        String line =  null;
        commandline = new HashMap<String, EnigmaRequest>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        while((line=br.readLine())!=null) {

            String str[] = line.split(";");

            if ((str.length > 5)) {

                EnigmaRequest e = new EnigmaRequest();

                e.setAlexaCommand(str[0]);
                e.setApi(str[1]);
                e.setParameter(str[2]);
                e.setOkText(str[3]);
                e.setErrorText(str[4]);
                e.setNextAlexaCommand(str[5]);

                commandline.put(str[0], e);
            }
        }
        log.info(String.format("Got %d REcords from CSV", commandline.size()));

    }

    public EnigmaRequest getCommand(String key) {
        EnigmaRequest e = new EnigmaRequest();

        e = commandline.get(key);

        return e;
    }
}
