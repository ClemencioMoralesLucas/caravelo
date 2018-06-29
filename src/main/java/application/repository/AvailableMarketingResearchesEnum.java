package application.repository;

import org.apache.commons.lang3.EnumUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clemencio Morales Lucas.
 */
public enum AvailableMarketingResearchesEnum {

    TGI,
    XYZ;

    public static List<String> asList() {
        final List<String> result = new ArrayList<>();
        List<AvailableMarketingResearchesEnum> availableMarketingResearchesList =
                EnumUtils.getEnumList(AvailableMarketingResearchesEnum.class);
        for (final AvailableMarketingResearchesEnum currentAvailableMarketingResearch:
                availableMarketingResearchesList) {
            result.add(currentAvailableMarketingResearch.toString());
        }
        return result;
    }
}
