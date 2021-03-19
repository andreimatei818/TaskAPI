package com.task.ING.validator;

import com.task.ING.exceptions.global.InvalidFormatException;
import com.task.ING.exceptions.global.ParameterCannotBeNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ValidatorRequestTransactions {

    private final Logger logger = LoggerFactory.getLogger(ValidatorRequestTransactions.class);

    public static void validateParameters(String accountId, String lastHours, String lastDays) {
        ValidatorRequestTransactions validatorRequestTransactions = new ValidatorRequestTransactions();
        validatorRequestTransactions.validateAccount(accountId);
        validatorRequestTransactions.validateDigits(lastHours, "lastHours");
        validatorRequestTransactions.validateDigits(lastDays, "lastDays");
    }

    private void validateDigits(String value, String parameter) {
        if (StringUtils.isEmpty(value)) {
            String message = "Parameter " + parameter + " cannot be empty";
            logger.info(message);
            throw new ParameterCannotBeNullException(message);
        }

        String pattern = "\\d+";
        if (!value.matches(pattern)) {
            String message = "Parameter " + parameter + " should be an integer";
            logger.info(message);
            throw new InvalidFormatException(message);
        }

    }

    private void validateAccount(String accountId) {
        if (StringUtils.isEmpty(accountId)) {
            throw new ParameterCannotBeNullException("Parameter accountId cannot be empty");
        }
    }


}
