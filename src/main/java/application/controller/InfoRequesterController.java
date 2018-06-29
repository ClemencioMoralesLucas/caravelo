package application.controller;

import application.controller.constants.GeneralControllerConstants;
import application.controller.constants.InfoRequesterControllerConstants;
import application.service.InfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Clemencio Morales Lucas.
 */

@RestController
public class InfoRequesterController implements ErrorController, InfoRequesterControllerConstants,
        GeneralControllerConstants {

    @Autowired
    private InfoProviderService infoProviderService;

    @Autowired
    private AuthController authController;

    @RequestMapping(value = "retrieveAvailableMarketingResearches", method = RequestMethod.GET)
    public List<String> retrieveAvailableMarketingResearches() {
        return infoProviderService.retrieveAvailableMarketingResearches();
    }

    @RequestMapping(value = "retrieveMarketingResearchFromProvider/{provider}", method = RequestMethod.GET)
    public String retrieveMarketingResearchFromProvider(@PathVariable final String provider) {
        return authController.isCaraveloPresent() ? infoProviderService.retrieveMarketingResearchFromProvider(provider) : SCARY_MESSAGE;
    }

    @RequestMapping(value = "retrieveEncryptedMarketingResearchFromTgi", method = RequestMethod.GET)
    public String retrieveEncryptedMarketingResearchFromTgi() {
        return infoProviderService.retrieveEncryptedMarketingResearchFromTgi();
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = "/error")
    public String error() {
        return BAD_REQUEST_MESSAGE;
    }

}
