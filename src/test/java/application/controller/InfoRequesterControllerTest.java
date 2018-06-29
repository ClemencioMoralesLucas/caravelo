package application.controller;

import application.repository.AvailableMarketingResearchesEnum;
import application.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.*;

/**
 * @README IMPORTANT: The server must be up and running in order to
 * execute this unit test.
 * Created by Clemencio Morales Lucas.
 */
public class InfoRequesterControllerTest {

    private InfoRequesterController infoRequesterController;

    @BeforeMethod
    public void setUp() throws Exception {
        infoRequesterController = new InfoRequesterController();
    }

    @Test
    public void testRetrieveAvailableMarketingResearches() throws Exception {
        final String result = get("/retrieveAvailableMarketingResearches").asString();
        assertTrue(result.contains(AvailableMarketingResearchesEnum.TGI.toString()));
        assertTrue(result.contains(AvailableMarketingResearchesEnum.XYZ.toString()));
    }

    @Test
    public void testRetrieveMarketingResearchFromProvider() throws Exception {
        final String result = get("/retrieveMarketingResearchFromProvider/TGI").asString();
        assertEquals(result, infoRequesterController.SCARY_MESSAGE);
    }

    @Test
    public void testRetrieveEncryptedMarketingResearchFromTgi() throws Exception {
        final String jwtToken = get("/retrieveEncryptedMarketingResearchFromTgi").asString();
        Jws<Claims> claimsJws = JwtUtils.decodeJWT(jwtToken);
        assertTrue(claimsJws.getBody().toString().contains("Caravelo"));
    }
}
