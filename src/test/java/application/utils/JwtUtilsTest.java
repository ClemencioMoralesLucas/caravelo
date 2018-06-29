package application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class JwtUtilsTest implements JwtUtilsTestConstants {

    private Map<String, Object> claimsMap;
    private String actualJwt;

    @BeforeMethod
    public void setUp() {
        initializeClaimsMap();
        this.actualJwt = JwtUtils.encodeJWT(claimsMap);
    }

    private void initializeClaimsMap() {
        claimsMap = new HashMap<>();
        claimsMap.put(NAME_ID, SNAKE_NAME);
        claimsMap.put(SURNAME_ID, SNAKE_SURNAME);
        claimsMap.put(AGE_ID, SNAKE_AGE);
        claimsMap.put(ENEMY_ID, SNAKE_ENEMY_NAME);
    }

    @Test
    public void testEncodeJWTGeneratesExpectedToken() {
        assertEquals(actualJwt, EXPECTED_JWT);
    }

    @Test
    public void testEncodeJWTGeneratesExpectedClaims() {
        final Claims claims = Jwts.parser().setSigningKey(JwtUtils.SECRET_KEY_BYTE)
                .parseClaimsJws(actualJwt).getBody();
        assertExpectedJwtBody(claims);
    }

    @Test
    public void testDecodeJWTGeneratesExpectedGeneralPayload() {
        final Jws<Claims> claims = JwtUtils.decodeJWT(EXPECTED_JWT);
        assertExpectedJwtFullIntegrity(claims);
    }

    @Test(expectedExceptions = SignatureException.class)
    public void testDecodeJWTThrowsExceptionIfWrongSignature() {
        final Jws<Claims> jwt = Jwts.parser().setSigningKey(FAKE_KEY.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(actualJwt);
        JwtUtils.decodeJWT(jwt.toString());
    }

    @Test
    public void testValidJwtTokensAreDetectedAsValid() {
        for (final String jwtToken: VALID_JWT_TOKENS) {
            assertTrue(JwtUtils.isValid(jwtToken));
        }
    }

    @Test
    public void testInvalidJwtTokensAreDetectedAsInvalid() {
        for (final String jwtToken: INVALID_JWT_TOKENS) {
            assertFalse(JwtUtils.isValid(jwtToken));
        }
    }

    private void assertExpectedJwtFullIntegrity(final Jws<Claims> claims) {
        assertExpectedJwtHeader(claims);
        assertExpectedJwtBody(claims.getBody());
        assertExpectedJwtSignature(claims);
    }

    private void assertExpectedJwtHeader(final Jws<Claims> claims) {
        assertEquals(claims.getHeader().getAlgorithm(), JwtUtils.SIGNATURE_ALGORITHM_HS256);
        assertEquals(claims.getHeader().getType(), JwtUtils.JWT_TOKEN_ID);
    }

    private void assertExpectedJwtBody(final Claims claims) {
        assertEquals(claims.get(NAME_ID), SNAKE_NAME);
        assertEquals(claims.get(SURNAME_ID), SNAKE_SURNAME);
        assertEquals(claims.get(AGE_ID), SNAKE_AGE);
        assertEquals(claims.get(ENEMY_ID), SNAKE_ENEMY_NAME);
    }

    private void assertExpectedJwtSignature(final Jws<Claims> claims) {
        assertEquals(JwtUtils.decodeJWT(actualJwt).getSignature(), claims.getSignature());
    }
}