package application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Clemencio Morales Lucas.
 */
@Service
public class InfoProviderService {

    @Autowired
    private MarketSurveysProviderService marketSurveysProviderService;

    public List<String> retrieveAvailableMarketingResearches() {
        return marketSurveysProviderService.retrieveAvailableMarketingResearches();
    }

    public String retrieveMarketingResearchFromProvider(final String provider) {
        return marketSurveysProviderService.retrieveMarketingResearchFromProvider(provider);
    }

    public String retrieveEncryptedMarketingResearchFromTgi() {
        return marketSurveysProviderService.retrieveEncryptedMarketingResearchFromTgi();
    }
}
