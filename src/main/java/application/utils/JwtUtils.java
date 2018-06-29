package application.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils implements JwtUtilsConstants {

    static final byte[] SECRET_KEY_BYTE = SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8);

    public static String encodeJWT(final Map<String, Object> claimsMap) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(MILLISECONDS_PER_DAY))
                .setClaims(claimsMap)
                .setHeader(assembleHeader())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY_BYTE)
                .compact();
    }

    public static Jws<Claims> decodeJWT(final String jwt) {
        return Jwts.parser().setSigningKey(SECRET_KEY_BYTE).parseClaimsJws(jwt);
    }

    private static Map<String, Object> assembleHeader() {
        final Map<String, Object> headers = new HashMap<>();
        headers.put(ALGORITHM_ID, SIGNATURE_ALGORITHM_HS256);
        headers.put(TOKEN_ID, JWT_TOKEN_ID);
        return headers;
    }

    private static Date generateExpirationDate(final long timeToLiveMillis) {
        return new Date(System.currentTimeMillis() + timeToLiveMillis);
    }

    public static boolean isValid(final String jwtToken) {
        try {
            return !StringUtils.isEmpty(jwtToken) && null != JwtUtils.decodeJWT(jwtToken);
        } catch (final MalformedJwtException mje) {
            return false;
        }
    }

    /**
     * @README: Even though this method is not being used, it has been left here purposefully
     * to illustrate the massive power of JWT in this kind of scenarios; translating our
     * Market Surveys to a simple String can be done with this one-liner.
     */
    public static String getJwtTokenFromObject(final Object object) {
        return JwtUtils.encodeJWT(new ObjectMapper().convertValue(object, Map.class));
    }
}
