package de.couchkiwi.enigmahub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.couchkiwi.enigmahub.model.AlexaRegister;
import de.couchkiwi.enigmahub.model.DumbAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class DumbAuthController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String greetingSubmit( @RequestParam(value="state", required=false) String state,
                                  @RequestParam(value="client_id", required=false) String client_id,
                                  @RequestParam(value="response_type", required=false) String response_type,
                                  @RequestParam(value="scope", required=false) String scope,
                                  @RequestParam(value="redirect_uri", required=false) String uri,
                                  Model model) {

        AlexaRegister alexaRegister = new AlexaRegister();

        alexaRegister.setState(state);
        alexaRegister.setClient_id(client_id);
        alexaRegister.setResponse_type(response_type);
        alexaRegister.setScope(scope);
        alexaRegister.setDumbToken("641109110001");
        alexaRegister.setUri(uri);

        model.addAttribute("alexaRegister", alexaRegister);

        return "result";
    }

    @PostMapping("/redirect")
    public String redirect(@ModelAttribute("alexaRegister") AlexaRegister alexaRegister) {
        String url = alexaRegister.getUri();
        String parameter = "?state=" + alexaRegister.getState() + "&code=" + alexaRegister.getDumbToken();
        RestTemplate restTemplate = new RestTemplate();

        log.debug(alexaRegister.getDumbToken());

        String result;
        try {
            log.debug(url + parameter);
            result = new ObjectMapper().readValue(restTemplate.getForObject(url + parameter, String.class), String.class);
        } catch (IOException e) {

            throw new RuntimeException();
        }

        return result;
    }

}

