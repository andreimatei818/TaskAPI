package com.task.ING.validator;

import com.task.ING.exceptions.transaction.InvalidAmountTransactionException;
import com.task.ING.exceptions.transaction.InvalidTypeTransactionException;
import com.task.ING.exceptions.transaction.TransactionParameterCannotBeNullExceptionException;
import com.task.ING.model.Transaction;
import com.task.ING.model.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidatorTransaction {
    private final Logger logger = LoggerFactory.getLogger(ValidatorTransaction.class);
    private static final String INVALID_PARAMETER = "invalid parameter {} ";
    private static final String TRANSACTION_TYPE = "transaction type";
    private static final String NULL_PARAMETER = "parameter {} cannot be null";
    private static final String INVALID_AMOUNT = "amount should be numeric";
    private static final String RECIPIENT_OF_THE_TRANSACTION = "toAccount";
    private static final String AMOUNT = "amount";
    private static final String PAYER_OF_THE_TRANSACTION = "fromAccount";

    public static void validateTransaction(Transaction transaction) {
        ValidatorTransaction validatorTransaction = new ValidatorTransaction();
        validatorTransaction.validateType(transaction);
        validatorTransaction.validateAmount(transaction);
        validatorTransaction.validateAccountTo(transaction);
        validatorTransaction.validateTransferTransaction(transaction);
    }


    private void validateTransferTransaction(Transaction transaction) {
        if (TransactionType.TRANSFER.name().equals(transaction.getType()) && StringUtils.isEmpty(transaction.getFromAccount())) {
            logger.info(NULL_PARAMETER, PAYER_OF_THE_TRANSACTION);
            throw new TransactionParameterCannotBeNullExceptionException(PAYER_OF_THE_TRANSACTION);
        }
    }

    private void validateAccountTo(Transaction transaction) {
        if (StringUtils.isEmpty(transaction.getToAccount())) {
            logger.info(NULL_PARAMETER, RECIPIENT_OF_THE_TRANSACTION);
            throw new TransactionParameterCannotBeNullExceptionException(RECIPIENT_OF_THE_TRANSACTION);
        }
    }

    ;

    private void validateAmount(Transaction transaction) {
        if (StringUtils.isEmpty(transaction.getAmount())) {
            logger.info(NULL_PARAMETER, AMOUNT);
            throw new TransactionParameterCannotBeNullExceptionException(AMOUNT);
        }

        String pattern = "^[\\+]{0,1}[0-9]+[\\.\\,][0-9]+$";
        String amount = String.valueOf(transaction.getAmount());
        if (!amount.matches(pattern)) {
            logger.info(INVALID_PARAMETER + INVALID_AMOUNT, AMOUNT);
            throw new InvalidAmountTransactionException(INVALID_AMOUNT);
        }

    }

    private void validateType(Transaction transaction) {
        if (StringUtils.isEmpty(transaction.getType())) {
            logger.info(NULL_PARAMETER, TRANSACTION_TYPE);
            throw new TransactionParameterCannotBeNullExceptionException(TRANSACTION_TYPE);
        }
        List<String> enumValues = Stream.of(TransactionType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (!enumValues.contains(transaction.getType())) {
            logger.info(INVALID_PARAMETER, TRANSACTION_TYPE);
            throw new InvalidTypeTransactionException("Invalid transaction type field. Authority values should be:" + Arrays.toString(TransactionType.values()));
        }
    }

}
