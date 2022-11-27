package org.katabank.models;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;


public class Account {
    public Account(BigDecimal balance, List<Statement> statements) {
        this.balance = balance;
        this.statements = statements;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    private BigDecimal balance = new BigDecimal("0.00");
    private List<Statement> statements = new LinkedList<>();

}
