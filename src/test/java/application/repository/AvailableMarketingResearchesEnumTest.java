package application.repository;

import application.pojos.utils.EnumTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by Clemencio Morales Lucas.
 */
public class AvailableMarketingResearchesEnumTest {

    private static final List<String> EXPECTED_VALUES = Arrays.asList("TGI", "XYZ");

    @Test
    public void testAvailableMarketingResearchesEnumTestIsConsistent() {
        assertTrue(EXPECTED_VALUES.equals(Arrays.asList(EnumTest.getNames(AvailableMarketingResearchesEnum.class))));
    }
}