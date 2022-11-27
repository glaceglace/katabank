package org.katabank.models;

import org.katabank.enums.ClientAction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class Statement {
    private ZonedDateTime timestamp;
    private BigDecimal balanceBefore;
    private BigDecimal balanceAfter;
    private ClientAction action;
    private BigDecimal amount;

    public Statement(ZonedDateTime timestamp,
                     BigDecimal balanceBefore,
                     BigDecimal balanceAfter,
                     ClientAction action,
                     BigDecimal amount
    ) {

        this.timestamp = timestamp;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.action = action;
        this.amount = amount;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(BigDecimal balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public ClientAction getAction() {
        return action;
    }

    public void setAction(ClientAction action) {
        this.action = action;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
