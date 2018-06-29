package application.repository;

import application.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Clemencio Morales Lucas.
 */

@Repository
public class MarketSurveysProviderRepository {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String JSON_EXTENSION = ".json";
    private static final String END_OF_LINE = "\n";
    private static final String ERROR_MESSAGE_CANNOT_CONTACT = "Cannot contact ";

    public List<String> retrieveAvailableMarketingResearches() {
        return AvailableMarketingResearchesEnum.asList();
    }

    /**
     * @README: For the sake of clarity, it's assumed that there's no analysis of the
     data in our side, hence a wrapper is not being created and the json will return
     as a plain String.
     */
    public String retrieveMarketingResearchFromProvider(final String provider) {
        try {
            return new String(Files.readAllBytes(Paths.get(RESOURCES_PATH + provider + JSON_EXTENSION)));
        } catch (IOException e) {
            return ERROR_MESSAGE_CANNOT_CONTACT + provider;
        }
    }

    public String retrieveEncryptedMarketingResearchFromTgi() {
        final Map<String, Object> marketingResearchMap = new HashMap<>();
        marketingResearchMap.put(AvailableMarketingResearchesEnum.TGI.name(),
                this.retrieveMarketingResearchFromProvider(AvailableMarketingResearchesEnum.TGI.name()).
                        replace(END_OF_LINE, StringUtils.EMPTY));
        return JwtUtils.encodeJWT(marketingResearchMap);
    }
}
