package de.couchkiwi.enigmahub;

import de.couchkiwi.enigmahub.model.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Component
public class DiscoverResponse {

    public AlexaDiscoverResponse discover(AlexaDiscoverRequest request) {

        AlexaDiscoverResponse response = new AlexaDiscoverResponse();

        AlexaDiscoverResponse.AlexaDiscoverEvent event = new AlexaDiscoverResponse.AlexaDiscoverEvent();
        AlexaCommonHeader header = new AlexaCommonHeader();
        AlexaCommonPayload payload = new AlexaCommonPayload();

        List<AlexaCommonEndpoints.Endpoints> endpoint = new ArrayList<>();
        String[] displayCategories = {"TV"};
        AlexaCommonEndpoints.Endpoints.Cookie cookie = new AlexaCommonEndpoints.Endpoints.Cookie();
        List<AlexaCommonEndpoints.Endpoints.Capabilities> capabilities = new ArrayList<AlexaCommonEndpoints.Endpoints.Capabilities>();

        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties speakerProperties = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties();
        AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties powerControllerProperties = new AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties();

        List<AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported> powerControllerSupported = new ArrayList<>();
        List<AlexaCommonEndpoints.Endpoints.Capabilities.CapProperties.Supported> speakerSupported = new ArrayList<>();

        header.setMessageId("GIVE_IT_A_TRY");
        header.setName("Discover.Response");
        header.setNamespace("Alexa.discovery");
        header.setPayloadVersion("3");

        event.setHeader(header);

        cookie.setExtradetail1("Detail 1");
        cookie.setExtradetail2("Detail 2");
        cookie.setExtradetail3("Detail 3");
        cookie.setExtradetail4("Detail 4");

        powerControllerSupported.add(setSupported("powerstate"));

        speakerSupported.add(setSupported("volume"));
        speakerSupported.add(setSupported("muted"));

        powerControllerProperties.setSupported(powerControllerSupported);
        powerControllerProperties.setProactivelyReported(true);
        powerControllerProperties.setProactivelyReported(true);

        capabilities.add(setcapabilities("AlexaInterface", "Alexa.PowerController", "3", powerControllerSupported, true, true));
        capabilities.add(setcapabilities("AlexaInterface", "Alexa.Speaker", "1.0", speakerSupported, false, false));

        endpoint.add(setEndpoint("ET9000", "Xtrend ET9000", "Enigma SAT-Receiver","Xtrend", displayCategories, cookie, capabilities)) ;

        payload.setEndpoint(endpoint);
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