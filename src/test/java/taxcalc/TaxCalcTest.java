package taxcalc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaxCalcTest {

    @Test
    public void canCalculateTax() throws Exception {
        Integer first = new TaxCalc(10).netAmount(new TaxCalc.Pair<>(40, "GBP"), new TaxCalc.Pair<>(50, "GBP"), new TaxCalc.Pair<>(60, "GBP")).first;
        assertEquals(135, first.intValue());
    }

    @Test
    public void cannotSumDifferentCurrencies() throws Exception {
        try {
            new TaxCalc(10).netAmount(new TaxCalc.Pair<>(4, "GBP"), new TaxCalc.Pair<>(5, "USD"));
            fail("didn't throw");
        } catch (Exception e) {

        }
    }
}