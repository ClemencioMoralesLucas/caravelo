package application.service;

import application.repository.MarketSurveysProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Clemencio Morales Lucas.
 */

@Service
public class MarketSurveysProviderService {

    @Autowired
    private MarketSurveysProviderRepository marketSurveysProviderRepository;

    public List<String> retrieveAvailableMarketingResearches() {
        return marketSurveysProviderRepository.retrieveAvailableMarketingResearches();
    }

    public String retrieveMarketingResearchFromProvider(final String provider) {
        return marketSurveysProviderRepository.retrieveMarketingResearchFromProvider(provider);
    }

    public String retrieveEncryptedMarketingResearchFromTgi() {
        return marketSurveysProviderRepository.retrieveEncryptedMarketingResearchFromTgi();
    }
}
