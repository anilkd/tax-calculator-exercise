package taxcalc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import taxcalc.TaxCalc.Money
import java.math.BigDecimal
import java.util.Currency

class TaxCalcTest {
    private val GBP = Currency.getInstance("GBP")
    private val USD = Currency.getInstance("USD")

    @Test
    fun `should calculate tax for single currency`() {
        val expectedNetAmount = Money(BigDecimal(135.00), GBP)
        val actualNetAmount =
            TaxCalc(10).netAmount(
                Money(BigDecimal(40), GBP),
                Money(BigDecimal(50), GBP),
                Money(BigDecimal(60), GBP)
            )
        assertThat(actualNetAmount).isEqualTo(expectedNetAmount)
    }

    @Test
    fun `should throw exception if multiple currencies are presented`() {
        val exception = assertThrows<ApplicationException> {
            TaxCalc(10).netAmount(Money(BigDecimal(40), GBP), Money(BigDecimal(40), USD))
        }

        assertThat(exception).hasMessage("Not a valid currency")

    }
}