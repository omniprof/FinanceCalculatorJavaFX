package com.cejv416.financecalculatorjavafx.data;

import java.math.BigDecimal;

/**
 * This bean class represents the input data for solving the three finance
 * calculations and the output data with the result. It is unchanged in the
 * JavaFX version.
 *
 * @author Ken Fogel
 * @version 1.0
 */
public class FinanceBean {

    private BigDecimal inputValue; //references
    private BigDecimal rate;
    private BigDecimal term;
    private BigDecimal result;

    public FinanceBean() {
        result = new BigDecimal("0");
        inputValue = new BigDecimal("0");
        rate = new BigDecimal("0");
        term = new BigDecimal("0");
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public BigDecimal getInputValue() {
        return inputValue;
    }

    public void setInputValue(BigDecimal inputValue) {
        this.inputValue = inputValue;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getTerm() {
        return term;
    }

    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "FinanceBean{" + "inputValue=" + inputValue + ", rate=" + rate + ", term=" + term + ", result=" + result + '}';
    }

}
