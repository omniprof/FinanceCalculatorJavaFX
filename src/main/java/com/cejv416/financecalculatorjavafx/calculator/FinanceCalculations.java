package com.cejv416.financecalculatorjavafx.calculator;

import com.cejv416.financecalculatorjavafx.data.FinanceBean;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class contains the three calculations. This method is unchanged from the
 * console version of this program
 *
 * @author Ken Fogel
 * @version 1.0
 */
public class FinanceCalculations {

    /**
     * The Loan calculation
     *
     * @param money A FinanceBean
     * @throws ArithmeticException If a string cannot be converted to a
     * BigDecimal it throws this exception
     */
    public void loanCalculation(FinanceBean money) throws ArithmeticException {

        // Divide APR by 12
        BigDecimal monthlyRate = money.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        // At each step this variable is updated
        BigDecimal temp;
        // (1+rate)
        temp = BigDecimal.ONE.add(monthlyRate);

        // (1+rate)^term
        temp = temp.pow(money.getTerm().intValueExact());

        // BigDecimal pow does not support negative exponents so divide 1 by the result
        temp = BigDecimal.ONE.divide(temp, MathContext.DECIMAL64);

        // 1 - (1+rate)^-term
        temp = BigDecimal.ONE.subtract(temp);

        // rate / (1 - (1+rate)^-term)
        temp = monthlyRate.divide(temp, MathContext.DECIMAL64);

        // pv * (rate / 1 - (1+rate)^-term)
        temp = money.getInputValue().multiply(temp);

        // Round to 2 decimal places using banker's rounding
        temp = temp.setScale(2, RoundingMode.HALF_EVEN);

        // Remove the sign if the result is negative
        money.setResult(temp.abs());
    }

    /**
     * The Future Value calculation
     *
     * @param money A FinanceBean
     * @throws ArithmeticException If a string cannot be converted to a
     * BigDecimal it throws this exception
     */
    public void futureValueCalculation(FinanceBean money) throws ArithmeticException {
        // Divide APR by 12
        BigDecimal monthlyRate = money.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        // At each step this variable is updated
        BigDecimal temp;
        // (1+rate)
        temp = BigDecimal.ONE.add(monthlyRate);

        // (1+rate)^term
        temp = temp.pow(money.getTerm().intValueExact());

        // 1 - (1+rate)^-term
        temp = BigDecimal.ONE.subtract(temp);

        // (1 - (1+rate)^-term) / rate
        temp = temp.divide(monthlyRate, MathContext.DECIMAL64);

        // pv * (rate / 1 - (1+rate)^-term)
        temp = money.getInputValue().multiply(temp);

        // Round to 2 decimal places using banker's rounding
        temp = temp.setScale(2, RoundingMode.HALF_EVEN);

        // Remove the sign if the result is negative
        money.setResult(temp.abs());
    }

    /**
     * The Savings Goal calculation
     *
     * @param money A FinanceBean
     * @throws ArithmeticException If a string cannot be converted to a
     * BigDecimal it throws this exception
     */
    public void savingsGoalCalculation(FinanceBean money) throws ArithmeticException {
        // Divide APR by 12
        BigDecimal monthlyRate = money.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        BigDecimal temp;

        // (1+rate)
        temp = BigDecimal.ONE.add(monthlyRate);

        // (1+rate)^term
        temp = temp.pow(money.getTerm().intValueExact());

        // 1 - ((1+rate)^term)
        temp = BigDecimal.ONE.subtract(temp);

        // rate / (1 - (1+rate)^term)
        temp = monthlyRate.divide(temp, MathContext.DECIMAL64);

        // fv * (rate / (1 - (1+rate)^term))
        temp = money.getInputValue().multiply(temp);

        temp = temp.setScale(2, RoundingMode.HALF_EVEN);

        // Remove the sign if the result is negative
        money.setResult(temp.abs());
    }
}
