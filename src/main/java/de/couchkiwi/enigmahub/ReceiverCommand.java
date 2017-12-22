package de.couchkiwi.enigmahub;

import de.couchkiwi.enigmahub.model.AmazonRequest;
import de.couchkiwi.enigmahub.model.AmazonResponse;
import de.couchkiwi.enigmahub.model.enigma.EnigmaRequest;
import de.couchkiwi.enigmahub.service.AlexaCommandService;
import de.couchkiwi.enigmahub.service.CallEnigmaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReceiverCommand {

    @Value(("${target_url}"))
    private String ENIGMA_URL;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ReceiverCommand() {
        log.info("Running Constructor");
    }

    @Autowired
    AlexaCommandService alexaCommandService;

    public AmazonResponse getCommand(AmazonRequest request) {

        String receiverCommand;
        String requestType;

        AmazonResponse amazonResponse = new AmazonResponse();
        AmazonResponse.Response amrs = new AmazonResponse.Response();
        AmazonResponse.Response.OutputSpeech amrso = new AmazonResponse.Response.OutputSpeech();
        amrso.setType("PlainText");

        requestType = request.getRequest().getType();
        log.info("Url ist: " + ENIGMA_URL);

        if (requestType.equals("IntentRequest")) {

            receiverCommand = request.getRequest().getIntent().getName();                                               // fetch command to process

            amrso.setText(this.call(receiverCommand, 1));

        } else
            amrso.setText("Ich verstehe die Anfrage nicht");

        amrs.setShouldEndSession(true);

        amazonResponse.setVersion("1.0");
        amrs.setOutputSpeech(amrso);
        amazonResponse.setResponse(amrs);

        return amazonResponse;
    }

    private String call(String commandToken, int iterations) {

        String responseText;
        EnigmaRequest e;
        CallEnigmaService callEnigmaService = new CallEnigmaService();

        e = alexaCommandService.getCommand(commandToken);

        if (e != null) {
            if (callEnigmaService.sendCommand(ENIGMA_URL, e.getApi(), e.getParameter())) {

                if (e.getNextAlexaCommand().length() > 2 && iterations < 5) {                                               // make sure, that we will terminate after 4 rounds latest.
                    responseText = this.call(e.getNextAlexaCommand(), iterations + 1);
                } else
                    responseText = e.getOkText();
            } else {
                responseText = e.getErrorText();
            }
        } else {
            responseText = "Diese Anweisung verstehe ich leider nicht!";
        }
        return responseText;
    }
}