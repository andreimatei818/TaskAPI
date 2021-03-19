package com.task.ING.service;


import com.task.ING.exceptions.account.AccountDoesNotExistsException;
import com.task.ING.exceptions.account.AccountParameterCannotBeNullException;
import com.task.ING.exceptions.account.InvalidAmountFormatException;
import com.task.ING.exceptions.account.InvalidStatusException;
import com.task.ING.model.Account;
import com.task.ING.model.AccountDTO;
import com.task.ING.model.AccountStatusDTO;
import com.task.ING.repository.AccountRepository;
import com.task.ING.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {


    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;


    @Test
    public void testCreateAccountWithoutUserId() {
        //given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAvailableAmount("12");
        try {
            //when
            Account actualAccount = accountService.createAccount(accountDTO);
        } catch (AccountParameterCannotBeNullException e) {
            // then
            assertEquals("Parameter userId is required and cannot be null", e.getMessage());
        }


    }

    @Test
    public void testCreateAccountWithoutAmount() {
        //given
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId("1");
        try {
            //when
            Account actualAccount = accountService.createAccount(accountDTO);
        } catch (AccountParameterCannotBeNullException e) {
            // then
            assertEquals("Parameter availableAmount is required and cannot be null", e.getMessage());
        }


    }

    @Test
    public void testCreateWrongFormatAmountAccount() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId("1");
        accountDTO.setAvailableAmount("asdad");
        try {
            //when
            Account actualAccount = accountService.createAccount(accountDTO);
        } catch (InvalidAmountFormatException e) {
            // then
            assertEquals("Amount should be numeric", e.getMessage());
        }
    }

    @Test
    public void testUpdateAccountWithWrongStatus() {
        //given
        AccountStatusDTO accountStatusDTO = new AccountStatusDTO();
        accountStatusDTO.setAccountId(1);
        accountStatusDTO.setStatus("WRONG STATUS");
        try {
            //when
            Account actualAccount = accountService.updateAccount(accountStatusDTO);
        } catch (InvalidStatusException e) {
            // then
            assertEquals("Invalid status type field. Status values should be:[ACTIVE, INACTIVE]", e.getMessage());
        }

    }


    @Test
    public void testUpdateAccountWithWrongId() {
        //given
        AccountStatusDTO accountStatusDTO = new AccountStatusDTO();
        accountStatusDTO.setAccountId(2);
        accountStatusDTO.setStatus("ACTIVE");

        when(accountRepository.findById(2)).thenReturn(Optional.empty());

        try {
            //when
            Account actualAccount = accountService.updateAccount(accountStatusDTO);
        } catch (AccountDoesNotExistsException e) {
            // then
            assertEquals("Account 2 does not exists", e.getMessage());
        }


    }

}