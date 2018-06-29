package application.controller.constants;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Clemencio Morales Lucas.
 */
public interface GeneralControllerConstants {

    String ERROR_PATH = "/error";
    String AMPERSAND_SYMBOL = "&";
    String EQUALS_SYMBOL = "=";
    String UTF_8 = "UTF-8";
    String BAD_REQUEST_MESSAGE = "Bad Request ¯\\_(ツ)_/¯";

    default Map<String, String> splitQuery(final String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<>();
        String[] pairs = query.split(AMPERSAND_SYMBOL);
        for (String pair : pairs) {
            int idx = pair.indexOf(EQUALS_SYMBOL);
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), UTF_8),
                    URLDecoder.decode(pair.substring(idx + 1), UTF_8));
        }
        return query_pairs;
    }
}
