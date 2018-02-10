package de.couchkiwi.enigmahub.service;

import de.couchkiwi.enigmahub.model.AlexaRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;

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

        Instant instant = Instant.now();
        Long timeStampSeconds = instant.getEpochSecond() - 1400000000;

        AlexaRegister alexaRegister = new AlexaRegister();

        alexaRegister.setState(state);
        alexaRegister.setClient_id(client_id);
        alexaRegister.setResponse_type(response_type);
        alexaRegister.setScope(scope);
        alexaRegister.setDumbToken(timeStampSeconds.toString());
        alexaRegister.setUri(uri);

        model.addAttribute("alexaRegister", alexaRegister);

        return "result";
    }
/*

 @RequestMapping("/to-be-redirected")
public RedirectView localRedirect() {
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://www.yahoo.com");
    return redirectView;
}
*/

    @RequestMapping("/redirect")
    public RedirectView redirectToExternalUrl(@ModelAttribute("alexaRegister") AlexaRegister alexaRegister) throws URISyntaxException {

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(alexaRegister.getUri() + "?state=" + alexaRegister.getState() + "&code=" + alexaRegister.getDumbToken());

        log.debug(redirectView.toString());

        return redirectView;
}

}

