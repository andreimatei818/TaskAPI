package com.task.ING.service;

import com.task.ING.exceptions.global.ParameterCannotBeNullException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Test()
    public void getAllTransactionWithParameterAccountIdNull() {
        //given
        String accountId = null;
        String lastHours = "3";
        String lastDays = "3";
        //when
        try {
            transactionService.getAllTransactionForAccountInTimeFrame(accountId, lastHours, lastDays);
        } catch (ParameterCannotBeNullException e) {
            //then
            Assert.assertEquals("Parameter accountId cannot be empty", e.getMessage());
        }
    }


    @Test()
    public void getAllTransactionWithParameterLastHoursNull() {
        //given
        String accountId = "1";
        String lastHours = null;
        String lastDays = "3";
        //when
        try {
            transactionService.getAllTransactionForAccountInTimeFrame(accountId, lastHours, lastDays);
        } catch (ParameterCannotBeNullException e) {
            //then
            Assert.assertEquals("Parameter lastHours cannot be empty", e.getMessage());
        }
    }


    @Test()
    public void getAllTransactionWithParameterLastDaysNull() {
        //given
        String accountId = "1";
        String lastHours = "2";
        String lastDays = null;
        //when
        try {
            transactionService.getAllTransactionForAccountInTimeFrame(accountId, lastHours, lastDays);
        } catch (ParameterCannotBeNullException e) {
            //then
            Assert.assertEquals("Parameter lastDays cannot be empty", e.getMessage());
        }
    }
}