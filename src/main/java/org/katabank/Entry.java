package org.katabank;

import org.katabank.exceptions.BankException;
import org.katabank.models.Account;
import org.katabank.operations.OperationDispatcher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Scanner;

public class Entry { // Seulement pour démo, pas de test pour cette classe
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        while (scan.hasNext()) {
            try {
                String order = scan.nextLine();
                if (!order.equalsIgnoreCase("print")) {
                    String[] orderArray = order.split("\\s+");
                    if (orderArray.length != 2) {
                        throw new BankException("Opération non validé");
                    } else {
                        String operation = orderArray[0];
                        if (!"deposit".equalsIgnoreCase(operation) && !"withdraw".equalsIgnoreCase(operation)) {
                            throw new BankException("Opération non validé:" + operation);
                        }
                        BigDecimal amount = new BigDecimal(orderArray[1]).setScale(2, RoundingMode.HALF_UP);
                        OperationDispatcher.dispatchOperation(operation, amount, account);
                    }
                } else {
                    OperationDispatcher.dispatchOperation(order, BigDecimal.ZERO, account);
                }
                System.out.println("Opération OK");
            } catch (BankException exception) {
                System.out.println(exception.getMessage());
            } catch (NumberFormatException exception) {
                System.out.println("Montant non validé");
            }
        }

    }
}
