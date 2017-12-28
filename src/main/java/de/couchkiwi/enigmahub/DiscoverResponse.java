package de.couchkiwi.enigmahub;

import de.couchkiwi.enigmahub.model.*;
import de.couchkiwi.enigmahub.model.enigma.EnigmaRequest;
import de.couchkiwi.enigmahub.service.AwsCredentialService;
import de.couchkiwi.enigmahub.service.ReceiverSubscribeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

public class DiscoverResponse {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AlexaDiscoverResponse discover(AlexaDiscoverRequest request, AwsCredentialService acs, ReceiverSubscribeService rss) {

        AlexaDiscoverResponse response = new AlexaDiscoverResponse();

        AlexaDiscoverResponse.AlexaDiscoverEvent event = new AlexaDiscoverResponse.AlexaDiscoverEvent();
        AlexaCommonHeader header = new AlexaCommonHeader();
        AlexaCommonPayload payload = new AlexaCommonPayload();

        String[] displayCategories = {"TV"};

        AlexaCommonEndpoints.Endpoints.Cookie cookie = new AlexaCommonEndpoints.Endpoints.Cookie();
        cookie.setExtradetail1("Detail 1");
        cookie.setExtraDetail2("Detail 2");
        cookie.setExtraDetail3("Detail 3");
        cookie.setExtradetail4("Detail 4");

        header.setMessageId(UUID.randomUUID().toString());
        header.setName("Discover.Response");
        header.setNamespace("Alexa.Discovery");
        header.setPayloadVersion("3");

        String token = request.getDirective().getPayload().getScope().getToken();

        String mailAddress = acs.getEmailFromCredential(token);

        SortedMap<String, EnigmaRequest> userEntries = rss.getAlexaUserlist().subMap(mailAddress, mailAddress+"\uFFFF");
        List<AlexaCommonEndpoints.Endpoints> endpoint = new ArrayList<>();

        for(Map.Entry entry: userEntries.entrySet()) {

            String userEndpoint =  userEntries.get(entry.getKey()).getReceiverModell();

            List<AlexaCommonEndpoints.Endpoints.Capabilities> capabilities = new ArrayList<AlexaCommonEndpoints.Endpoints.Capabilities>();

            List<String> userCapabilities = userEntries.get(entry.getKey()).getCapabilities();

            for (String c : userCapabilities) {

                switch (c) {
                    case "Alexa.PowerController":
                        List<AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported> powerControllerSupported = new ArrayList<>();
                        powerControllerSupported.add(setSupported("powerState"));

                        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties powerControllerProperties = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties();
                        powerControllerProperties.setSupported(powerControllerSupported);
                        powerControllerProperties.setProactivelyReported(true);
                        powerControllerProperties.setRetrievable(true);

                        capabilities.add(setcapabilities(
                                "AlexaInterface",
                                "Alexa.PowerController",
                                "3", powerControllerSupported,
                                true,
                                true));
                        break;

                    case "Alexa.Speaker":
                        List<AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported> speakerSupported = new ArrayList<>();
                        speakerSupported.add(setSupported("volume"));
                        speakerSupported.add(setSupported("muted"));

                        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties speakerProperties = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties();
                        speakerProperties.setSupported(speakerSupported);
                        capabilities.add(setcapabilities(
                                "AlexaInterface",
                                "Alexa.Speaker",
                                "1.0",
                                speakerSupported,
                                false,
                                false));
                        break;
                }
            }

            endpoint.add(setEndpoint(userEndpoint,
                    userEndpoint,
                    "Enigma SAT-Receiver",
                    "Enigma",
                    displayCategories,
                    cookie,
                    capabilities));

        }

        event.setHeader(header);
        payload.setEndpoints(endpoint);
        event.setHeader(header);
        event.setPayload(payload);
        response.setEvent(event);

        return response;

    }

    private AlexaCommonEndpoints.Endpoints setEndpoint
            (String endpointId,
             String friendlyName,
             String description,
             String manufactorerName,
             String[] displayCategories,
             AlexaCommonEndpoints.Endpoints.Cookie cookie,
             List<AlexaCommonEndpoints.Endpoints.Capabilities> capabilities) {

        AlexaCommonEndpoints.Endpoints endpoint = new AlexaCommonEndpoints.Endpoints();

        endpoint.setEndpointId(endpointId);
        endpoint.setFriendlyName(friendlyName);
        endpoint.setDescription(description);
        endpoint.setManufacturerName(manufactorerName);
        endpoint.setDisplayCategories(displayCategories);
        endpoint.setCookie(cookie);
        endpoint.setCapabilities(capabilities);

        return endpoint;

    }

    private AlexaCommonEndpoints.Endpoints.Capabilities setcapabilities
            (String type,
             String l_interface,
             String version,
             List<AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported> supported,
             boolean proactivelyReported,
             boolean retrievable) {

        AlexaCommonEndpoints.Endpoints.Capabilities cap = new AlexaCommonEndpoints.Endpoints.Capabilities();
        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties prop = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties();

        prop.setSupported(supported);
        prop.setProactivelyReported(proactivelyReported);
        prop.setRetrievable(retrievable);

        cap.setType(type);
        cap.setL_interface(l_interface);
        cap.setVersion(version);
        cap.setProperties(prop);

        return cap;
    }

    private AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported setSupported (String name) {

        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported supported = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported();

        supported.setName(name);

        return supported;
    }

}