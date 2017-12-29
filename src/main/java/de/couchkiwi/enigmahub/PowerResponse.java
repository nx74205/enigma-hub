package de.couchkiwi.enigmahub;

import de.couchkiwi.enigmahub.model.AlexaDiscoverRequest;
import de.couchkiwi.enigmahub.model.AlexaDiscoverResponse;
import de.couchkiwi.enigmahub.model.AlexaPowerRequest;
import de.couchkiwi.enigmahub.model.AlexaPowerResponse;
import de.couchkiwi.enigmahub.service.AwsCredentialService;
import de.couchkiwi.enigmahub.service.ClientRestService;
import de.couchkiwi.enigmahub.service.ReceiverSubscribeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PowerResponse {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AlexaPowerResponse respond(AlexaPowerRequest request, AwsCredentialService acs, ReceiverSubscribeService rss) {

        AlexaPowerResponse response = new AlexaPowerResponse();

        AlexaPowerResponse.PowerContext context = new AlexaPowerResponse.PowerContext();
        AlexaPowerResponse.PowerContext.PowerContextProperties[] properties = new AlexaPowerResponse.PowerContext.PowerContextProperties[1];

        AlexaPowerResponse.PowerEvent event = new AlexaPowerResponse.PowerEvent();
        AlexaPowerResponse.PowerEvent.PowerEventHeader header = new AlexaPowerResponse.PowerEvent.PowerEventHeader();
        AlexaPowerResponse.PowerEvent.PowerEventEndpoint endpoint = new AlexaPowerResponse.PowerEvent.PowerEventEndpoint();
        AlexaPowerResponse.PowerEvent.PowerEventPayload payload = new AlexaPowerResponse.PowerEvent.PowerEventPayload();

        properties[0] = new AlexaPowerResponse.PowerContext.PowerContextProperties();
        properties[0].setNamespace("Alexa.PowerController");
        properties[0].setName("powerState");
        properties[0].setUncertaintyInMilliseconds(500);
        properties[0].setTimeOfSample(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSX").withZone(ZoneOffset.UTC).format(Instant.now()));

        header.setNamespace("Alexa");
        header.setName("Response");
        header.setPayloadVersion("3");
        header.setCorrelationToken(request.getDirective().getHeader().getCorrelationToken());
        header.setMessageId(UUID.randomUUID().toString());

        endpoint.setEndpointId(request.getDirective().getEndpoint().getEndpointId());

        String mailAddress = acs.getEmailFromCredential(request.getDirective().getEndpoint().getScope().getToken());
        String receiverModell = request.getDirective().getEndpoint().getEndpointId();

        switch (request.getDirective().getHeader().getName()) {
            case "TurnOn":
                if (!rss.setResult(mailAddress, receiverModell, "SwitchOn")) {
                    header.setName("ErrorResponse");
                    payload.setType("ENDPOINT_UNREACHABLE");
                    payload.setMessage("Der Receiver ist leider im Moment nicht erreichbar");
                    event.setPayload(payload);
                } else {
                    properties[0].setValue("ON");

                }

                break;

            case "TurnOff" :
                if (!rss.setResult(mailAddress, receiverModell, "SwitchOff")) {
                    header.setName("ErrorResponse");
                    payload.setType("ENDPOINT_UNREACHABLE");
                    payload.setMessage("Der Receiver ist leider im Moment nicht erreichbar");
                    event.setPayload(payload);
                } else {
                    properties[0].setValue("OFF");

                }

                break;
        }

        context.setProperties(properties);

        event.setHeader(header);
        event.setEndpoint(endpoint);

        response.setContext(context);
        response.setEvent(event);

        return response;
    }
}
