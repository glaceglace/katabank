package org.katabank.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.katabank.models.Account;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class OperationDispatcherTest {
    MockedStatic<Operations> operationsMockedStatic;

    @BeforeEach
    void init() {
        operationsMockedStatic = Mockito.mockStatic(Operations.class);
    }

    @AfterEach
    void end() {

        operationsMockedStatic.close();
    }

    @Test
    @DisplayName("when operation is deposit then it shall deposit")
    public void testDeposit() {

        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        OperationDispatcher.dispatchOperation("deposit", new BigDecimal("1.23"), account);
        operationsMockedStatic.verify(()->Operations.depot(any(), any()), times(1));
        operationsMockedStatic.verify(()->Operations.withdraw(any(), any()), times(0));
        operationsMockedStatic.verify(()->Operations.printStatement(any()), times(0));
    }

    @Test
    @DisplayName("when operation is withdraw then it shall deposit")
    public void testWithdraw() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        OperationDispatcher.dispatchOperation("withdraw", new BigDecimal("1.23"), account);
        operationsMockedStatic.verify(()->Operations.depot(any(), any()), times(0));
        operationsMockedStatic.verify(()->Operations.withdraw(any(), any()), times(1));
        operationsMockedStatic.verify(()->Operations.printStatement(any()), times(0));
    }
    @Test
    @DisplayName("when operation is print then it shall deposit")
    public void testPrint() {
        Account account = new Account(new BigDecimal("0.00"), new LinkedList<>());
        OperationDispatcher.dispatchOperation("print", new BigDecimal("1.23"), account);
        operationsMockedStatic.verify(()->Operations.depot(any(), any()), times(0));
        operationsMockedStatic.verify(()->Operations.withdraw(any(), any()), times(0));
        operationsMockedStatic.verify(()->Operations.printStatement(any()), times(1));
    }

}