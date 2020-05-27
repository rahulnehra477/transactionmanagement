package com.example.transactionmanagement.Dao;


import com.example.transactionmanagement.Dao.exception.DAOException;
import com.example.transactionmanagement.Model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Object> deleteByCustomerId(String customerId) throws DAOException;

    Flux<Object> findAllByCustomerId(String customerId) throws DAOException;
}
