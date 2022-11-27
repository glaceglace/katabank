package org.katabank.operations;

import org.katabank.enums.ClientAction;
import org.katabank.exceptions.BankException;
import org.katabank.models.Account;
import org.katabank.models.Statement;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class Operations {
    public static void depot(BigDecimal amount, Account account) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BankException("Le montant doit être plus que 0 et ne doit pas être null");
        }
        BigDecimal balanceAfter = account.getBalance().add(amount);
        Statement statement = new Statement(ZonedDateTime.now(), account.getBalance(), balanceAfter, ClientAction.DEPOT, amount);
        account.getStatements().add(statement);
        account.setBalance(balanceAfter);
    }

    public static void withdraw(BigDecimal amount, Account account) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new BankException("Le montant ne doit pas être 0 ou null");
        }
        BigDecimal balanceAfter = account.getBalance().add(amount.abs().negate());
        if (balanceAfter.compareTo(BigDecimal.ZERO) < 0) {
            throw new BankException("La solde n'est pas suffisante");
        }
        Statement statement = new Statement(ZonedDateTime.now(), account.getBalance(), balanceAfter, ClientAction.WITHDRAW, amount.abs().negate());
        account.getStatements().add(statement);
        account.setBalance(balanceAfter);
    }

    public static void printStatement(Account account) {
        if (account == null) {
            throw new BankException("Account ne doit pas être null");
        }


        List<Statement> statements = account.getStatements();
        statements.forEach(it -> {
            System.out.println(it.getAction() + "\tAvant: " + it.getBalanceBefore() + "\t Montant: " + it.getAmount() + "\tAprès: " + it.getBalanceAfter() + "\tTemps: " + it.getTimestamp().toLocalDateTime().withNano(0));
        });
        String balance = "Sold: " + account.getBalance();
        System.out.println(balance);
    }
}
