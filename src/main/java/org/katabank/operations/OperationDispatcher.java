package org.katabank.operations;

import org.katabank.exceptions.BankException;
import org.katabank.models.Account;

import java.math.BigDecimal;

public class OperationDispatcher {
    public static void dispatchOperation(String operation, BigDecimal amount, Account account) {
        if (operation.equalsIgnoreCase("deposit")) {
            Operations.depot(amount, account);
        } else if (operation.equalsIgnoreCase("withdraw")) {
            Operations.withdraw(amount, account);
        } else if (operation.equalsIgnoreCase("print")) {
            Operations.printStatement(account);
        } else {
            throw new BankException("Erreur inconnue");
        }
    }
}
