package org.katabank.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.katabank.exceptions.BankException;
import org.katabank.models.Account;
import org.katabank.models.Statement;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.katabank.enums.ClientAction.DEPOT;
import static org.katabank.enums.ClientAction.WITHDRAW;


class OperationsTest {

    @Test
    @DisplayName("when depot a null amount, then it shall throws a BankException")
    void testDepotNullAmount() {
        Account account = new Account(null, new LinkedList<>());
        BankException ex = assertThrows(BankException.class, () -> Operations.depot(new BigDecimal("-11.11"), account));
        assertThat(ex.getMessage()).isEqualTo("Le montant doit être plus que 0 et ne doit pas être null");
    }
    @Test
    @DisplayName("when depot a negative amount, then it shall throws a BankException")
    void testDepotNegativeAmount() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        BankException ex = assertThrows(BankException.class, () -> Operations.depot(new BigDecimal("-11.11"), account));
        assertThat(ex.getMessage()).isEqualTo("Le montant doit être plus que 0 et ne doit pas être null");
    }

    @Test
    @DisplayName("when depot a 0 amount, then it shall throws a BankException")
    void testDepo0Amount() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        BankException ex = assertThrows(BankException.class, () -> Operations.depot(new BigDecimal("0.00"), account));
        assertThat(ex.getMessage()).isEqualTo("Le montant doit être plus que 0 et ne doit pas être null");
    }

    @Test
    @DisplayName("when depot a positive amount, then the balance shall increase and add a new line of statement")
    void testDepotPositveAmount() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        Operations.depot(new BigDecimal("20.19"), account);
        assertThat(account.getStatements().size()).isEqualTo(1);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("20.19"));

        Statement statement = account.getStatements().get(0);
        assertThat(statement.getAmount()).isEqualTo(new BigDecimal("20.19"));
        assertThat(statement.getAction()).isEqualTo(DEPOT);
        assertThat(statement.getBalanceBefore()).isEqualTo(new BigDecimal("0.00"));
        assertThat(statement.getBalanceAfter()).isEqualTo(new BigDecimal("20.19"));
        assertThat(statement.getTimestamp()).isNotNull();

    }

    @Test
    @DisplayName("when depot multiple positive amount, then the balance shall increase and add same number of line of statement")
    void testDepotPositveAmount2() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        Operations.depot(new BigDecimal("11.11"), account);
        Operations.depot(new BigDecimal("22.22"), account);
        Operations.depot(new BigDecimal("33.33"), account);
        assertThat(account.getStatements().size()).isEqualTo(3);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("66.66"));

        Statement statement = account.getStatements().get(0);
        assertThat(statement.getAmount()).isEqualTo(new BigDecimal("11.11"));
        assertThat(statement.getAction()).isEqualTo(DEPOT);
        assertThat(statement.getBalanceBefore()).isEqualTo(new BigDecimal("0.00"));
        assertThat(statement.getBalanceAfter()).isEqualTo(new BigDecimal("11.11"));
        assertThat(statement.getTimestamp()).isNotNull();

        Statement statement2 = account.getStatements().get(1);
        assertThat(statement2.getAmount()).isEqualTo(new BigDecimal("22.22"));
        assertThat(statement2.getAction()).isEqualTo(DEPOT);
        assertThat(statement2.getBalanceBefore()).isEqualTo(new BigDecimal("11.11"));
        assertThat(statement2.getBalanceAfter()).isEqualTo(new BigDecimal("33.33"));
        assertThat(statement2.getTimestamp()).isNotNull();

        Statement statement3 = account.getStatements().get(2);
        assertThat(statement3.getAmount()).isEqualTo(new BigDecimal("33.33"));
        assertThat(statement3.getAction()).isEqualTo(DEPOT);
        assertThat(statement3.getBalanceBefore()).isEqualTo(new BigDecimal("33.33"));
        assertThat(statement3.getBalanceAfter()).isEqualTo(new BigDecimal("66.66"));
        assertThat(statement3.getTimestamp()).isNotNull();
        Operations.printStatement(account);

    }

    @Test
    @DisplayName("when withdraw a null amount, then it shall throws a BankException")
    void testWithdrawNullAmount() {
        Account account = new Account(new BigDecimal("66.66"), new LinkedList<>());
        BankException ex = assertThrows(BankException.class, () -> Operations.withdraw(null, account));
        assertThat(ex.getMessage()).isEqualTo("Le montant ne doit pas être 0 ou null");
    }

    @Test
    @DisplayName("when withdraw a 0 amount, then it shall throws a BankException")
    void testWithdraw0Amount() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        BankException ex = assertThrows(BankException.class, () -> Operations.withdraw(new BigDecimal("0.00"), account));
        assertThat(ex.getMessage()).isEqualTo("Le montant ne doit pas être 0 ou null");
    }

    @Test
    @DisplayName("when withdraw a positive amount, then the balance shall increase and add a new line of statement")
    void testWithdrawPositveAmount() {
        Account account = new Account(new BigDecimal("20.20"), new LinkedList<>());
        Operations.withdraw(new BigDecimal("20.19"), account);
        assertThat(account.getStatements().size()).isEqualTo(1);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("0.01"));

        Statement statement = account.getStatements().get(0);
        assertThat(statement.getAmount()).isEqualTo(new BigDecimal("-20.19"));
        assertThat(statement.getAction()).isEqualTo(WITHDRAW);
        assertThat(statement.getBalanceBefore()).isEqualTo(new BigDecimal("20.20"));
        assertThat(statement.getBalanceAfter()).isEqualTo(new BigDecimal("0.01"));
        assertThat(statement.getTimestamp()).isNotNull();

    }
    @Test
    @DisplayName("when withdraw a negative amount, then the balance shall increase and add a new line of statement")
    void testWithdrawNegativeAmount() {
        Account account = new Account(new BigDecimal("20.20"), new LinkedList<>());
        Operations.withdraw(new BigDecimal("-20.19"), account);
        assertThat(account.getStatements().size()).isEqualTo(1);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("0.01"));

        Statement statement = account.getStatements().get(0);
        assertThat(statement.getAmount()).isEqualTo(new BigDecimal("-20.19"));
        assertThat(statement.getAction()).isEqualTo(WITHDRAW);
        assertThat(statement.getBalanceBefore()).isEqualTo(new BigDecimal("20.20"));
        assertThat(statement.getBalanceAfter()).isEqualTo(new BigDecimal("0.01"));
        assertThat(statement.getTimestamp()).isNotNull();

    }

    @Test
    @DisplayName("when withdraw multiple positive amount, then the balance shall decrease and add same number of line of statement")
    void testWithdrawPositveAmount2() {
        Account account = new Account(new BigDecimal("77.77"), new LinkedList<>());
        Operations.withdraw(new BigDecimal("11.11"), account);
        Operations.withdraw(new BigDecimal("22.22"), account);
        Operations.withdraw(new BigDecimal("33.33"), account);
        assertThat(account.getStatements().size()).isEqualTo(3);
        assertThat(account.getBalance()).isEqualTo(new BigDecimal("11.11"));

        Statement statement = account.getStatements().get(0);
        assertThat(statement.getAmount()).isEqualTo(new BigDecimal("-11.11"));
        assertThat(statement.getAction()).isEqualTo(WITHDRAW);
        assertThat(statement.getBalanceBefore()).isEqualTo(new BigDecimal("77.77"));
        assertThat(statement.getBalanceAfter()).isEqualTo(new BigDecimal("66.66"));
        assertThat(statement.getTimestamp()).isNotNull();

        Statement statement2 = account.getStatements().get(1);
        assertThat(statement2.getAmount()).isEqualTo(new BigDecimal("-22.22"));
        assertThat(statement2.getAction()).isEqualTo(WITHDRAW);
        assertThat(statement2.getBalanceBefore()).isEqualTo(new BigDecimal("66.66"));
        assertThat(statement2.getBalanceAfter()).isEqualTo(new BigDecimal("44.44"));
        assertThat(statement2.getTimestamp()).isNotNull();

        Statement statement3 = account.getStatements().get(2);
        assertThat(statement3.getAmount()).isEqualTo(new BigDecimal("-33.33"));
        assertThat(statement3.getAction()).isEqualTo(WITHDRAW);
        assertThat(statement3.getBalanceBefore()).isEqualTo(new BigDecimal("44.44"));
        assertThat(statement3.getBalanceAfter()).isEqualTo(new BigDecimal("11.11"));
        assertThat(statement3.getTimestamp()).isNotNull();


    }

    @Test
    @DisplayName("when print a null account, then it shall throws a BankException")
    void testPrintNullAmount() {
        BankException ex = assertThrows(BankException.class, () -> Operations.printStatement(null));
        assertThat(ex.getMessage()).isEqualTo("Account ne doit pas être null");
    }
}