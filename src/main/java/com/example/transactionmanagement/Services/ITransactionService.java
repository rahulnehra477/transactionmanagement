package com.example.transactionmanagement.Services;

import com.example.transactionmanagement.Dao.exception.DAOException;
import com.example.transactionmanagement.Model.Transaction;

import com.example.transactionmanagement.Services.exception.ServiceException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {

    Mono<Transaction> createTransaction(Transaction transaction) throws ServiceException;

    Mono<Boolean> validateAndUpdateAccount(Transaction transaction) throws ServiceException;

    Flux<Object> deleteByCustomerId(String customerId) throws DAOException;

    Flux<Object> getByCustomerId(String customerId) throws DAOException;
}
