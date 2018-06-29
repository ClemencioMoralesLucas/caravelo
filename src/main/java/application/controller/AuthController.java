package application.controller;

import application.controller.constants.AuthControllerConstants;
import application.controller.constants.GeneralControllerConstants;
import application.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by Clemencio Morales Lucas.
 */

@RestController
public class AuthController implements AuthControllerConstants, GeneralControllerConstants {

    private boolean caraveloPresent = false;

    @Autowired
    private PasswordService passwordService;

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String createNewUserAndPassword(@RequestBody final String body) {
        try {
            Map<String, String> queryStringMap = splitQuery(body);
            final String name = queryStringMap.get(USER);
            final String password = queryStringMap.get(PASSWORD);
            return passwordService.createNewUserAndPassword(name, password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return ERROR_PATH;
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        this.caraveloPresent = false;
        return LOGOUT_MESSAGE;
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody final String body) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> queryStringMap = splitQuery(body);
        final String name = queryStringMap.get(USER);
        final String password = queryStringMap.get(PASSWORD);
        final String result = passwordService.loginUser(name, password);
        if (result.contains(PasswordService.WELCOME_MESSAGE + CARAVELO_NAME)) {
            this.caraveloPresent = true;
        }
        return result;
    }

    public boolean isCaraveloPresent() {
        return caraveloPresent;
    }

}
