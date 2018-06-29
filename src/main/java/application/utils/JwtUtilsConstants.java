package application.utils;

import io.jsonwebtoken.SignatureAlgorithm;

public interface JwtUtilsConstants {

    String TOKEN_ID = "typ";
    String SECRET_KEY_STRING = "secret";
    String ALGORITHM_ID = "alg";
    int MILLISECONDS_PER_DAY = 86_400_000;
    String SIGNATURE_ALGORITHM_HS256 = SignatureAlgorithm.HS256.getValue();
    String JWT_TOKEN_ID = "JWT";

}
