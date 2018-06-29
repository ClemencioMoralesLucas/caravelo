package application.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public interface JwtUtilsTestConstants {

    String NAME_ID = "name";
    String SURNAME_ID = "surname";
    String AGE_ID = "age";
    String ENEMY_ID = "enemy";
    String SNAKE_NAME = "Solid";
    String SNAKE_SURNAME = "Snake";
    String SNAKE_AGE = "42";
    String SNAKE_ENEMY_NAME = "Revolver Ocelot";
    String FAKE_KEY = "FAKE_KEY";
    String EXPECTED_JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdXJuYW1lIjoiU25ha2UiLCJuYW1lIjoiU2"
            + "9saWQiLCJlbmVteSI6IlJldm9sdmVyIE9jZWxvdCIsImFnZSI6IjQyIn0.AVoJJpLPE6ke-CDKVfOJBW8m72pgvZvhreUPwsUvQGk";
    String HEADERLESS_JWT = "eyJlbmVteSI6IlJldm9sdmVyIE9jZWxv"
            + "dCIsImFnZSI6IjQyIiwibmFtZSI6IlNvbGlkIiwic3VybmFtZSI6IlNuYWtlIn0.EVnCoEFQrhcz0zqcyMYao0iHsQ93WNnS2UWOXTtNza8";
    String PAYLOADLESS_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.EVnCoEFQrhcz0zqcyMYao0iHsQ93WNnS2UWOXTtNza8";
    String UNSIGNED_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbmVteSI6IlJldm9sdmVyIE9jZWxvdCIsImFnZSI6IjQyIiwibm"
            + "FtZSI6IlNvbGlkIiwic3VybmFtZSI6IlNuYWtlIn0";
    String[] VALID_JWT_TOKENS = {EXPECTED_JWT};
    String[] INVALID_JWT_TOKENS = {StringUtils.EMPTY, null, StandardCharsets.UTF_8.name(),
        HEADERLESS_JWT, PAYLOADLESS_JWT, UNSIGNED_JWT};
}
