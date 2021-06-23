package taxcalc

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Currency

internal class TaxCalc(var percent: Int) {
    fun netAmount(firstTaxableAmount: Money, vararg taxableAmounts: Money): Money {
        return listOf(firstTaxableAmount, *taxableAmounts)
            .fold(BigDecimal(0)) { totalGross, taxableAmount ->
                if (taxableAmount.currency != firstTaxableAmount.currency) throw ApplicationException("Not a valid currency")
                else totalGross.plus(taxableAmount.amount)
            }
            .let { totalGross ->
                Money(calculateNetAmount(totalGross), firstTaxableAmount.currency)
            }
    }

    private fun calculateNetAmount(totalGross: BigDecimal) = (totalGross.minus(calculateTax(totalGross)))

    private fun calculateTax(taxableAmount: BigDecimal) = (taxableAmount.multiply(BigDecimal(percent / 100.0)))

    internal data class Money(var amount: BigDecimal, val currency: Currency) {
        init {
            amount = amount.setScale(2, RoundingMode.HALF_UP)
        }
    }
}