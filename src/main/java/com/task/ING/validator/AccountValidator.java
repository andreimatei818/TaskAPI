package com.task.ING.validator;

import com.task.ING.exceptions.account.AccountParameterCannotBeNullException;
import com.task.ING.exceptions.account.InvalidAmountFormatException;
import com.task.ING.exceptions.account.InvalidStatusException;
import com.task.ING.exceptions.global.InvalidFormatException;
import com.task.ING.model.AccountDTO;
import com.task.ING.model.AccountStatus;
import com.task.ING.model.AccountStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccountValidator {
    //    public static final String PARAMETER_AVAILABLE_AMOUNT_IS_REQUIRED_AND_CANNOT_BE_NULL = "Parameter availableAmount is required and cannot be null";
//    public static final String PARAMETER_ACCOUNT_ID_IS_REQUIRED_AND_CANNOT_BE_NULL = "Parameter accountId is required and cannot be null";
//    public static final String PARAMETER_USER_ID_IS_REQUIRED_AND_CANNOT_BE_NULL = "Parameter userId is required and cannot be null";
    private final Logger logger = LoggerFactory.getLogger(AccountValidator.class);
    private static final String ACCOUNT_ID = "accountId";
    private static final String USER_ID = "userId";
    private static final String INVALID_PARAMETER = "invalid parameter {} . ";
    private static final String INVALID_AMOUNT = "Amount should be numeric";
    private static final String AMOUNT = "availableAmount";
    private static final String STATUS = "status";

    public static void validate(AccountDTO account) {
        AccountValidator accountValidator = new AccountValidator();
        accountValidator.validateDigits(account.getUserId());
        accountValidator.validateAmount(account.getAvailableAmount());
    }

    public static void validateUpdateStatus(AccountStatusDTO accountStatusDTO) {
        AccountValidator accountValidator = new AccountValidator();
        accountValidator.validateAccountId(accountStatusDTO.getAccountId());
        accountValidator.validateStatus(accountStatusDTO.getStatus());
    }

    private void validateStatus(String status) {
        List<String> enumValues = Stream.of(AccountStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (!enumValues.contains(status)) {
            logger.info(INVALID_PARAMETER, STATUS);
            throw new InvalidStatusException("Invalid status type field. Status values should be:" + Arrays.toString(AccountStatus.values()));
        }
    }

    private void validateAccountId(int accountId) {
        if (StringUtils.isEmpty(accountId)) {
            logger.info(INVALID_PARAMETER, ACCOUNT_ID);
            throw new AccountParameterCannotBeNullException(ACCOUNT_ID);
        }
    }

    private void validateAmount(String amount) {
        if (StringUtils.isEmpty(amount)) {
            logger.info(INVALID_PARAMETER, AMOUNT);
            throw new AccountParameterCannotBeNullException(AMOUNT);
        }

        String pattern = "\\d+";
        if (!amount.matches(pattern)) {
            logger.info(INVALID_PARAMETER + INVALID_AMOUNT, AMOUNT);
            throw new InvalidAmountFormatException(INVALID_AMOUNT);
        }
    }

    private void validateDigits(String value) {
        if (StringUtils.isEmpty(value)) {
            logger.info(INVALID_PARAMETER, USER_ID);
            throw new AccountParameterCannotBeNullException(USER_ID);
        }

        String pattern = "\\d+";
        if (!value.matches(pattern)) {
            String message = "Parameter " + value + " should be an integer";
            logger.info(message);
            throw new InvalidFormatException(message);
        }

    }


}
