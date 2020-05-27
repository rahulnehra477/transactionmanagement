package com.example.transactionmanagement.Services;

import com.example.transactionmanagement.Dao.exception.DAOException;
import com.example.transactionmanagement.Dao.TransactionRepository;
import com.example.transactionmanagement.Model.Transaction;
import com.example.transactionmanagement.Services.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Override
    public Mono<Transaction> createTransaction(Transaction transaction) throws ServiceException{
        transaction.setTransactionRefNum(this.generateTransactionNumber());
        return transactionRepository.save(transaction);

    }

    @Override
    public Mono<Boolean> validateAndUpdateAccount(Transaction transaction) throws ServiceException {
        Mono<Boolean> result = webClientBuilder.build().post()
                .uri( "http://account/account/validate/update" )
                .body( BodyInserters.fromObject( transaction ) )
                .retrieve().bodyToMono(Boolean.class);

        return result;
    }

    @Override
    public Flux<Object> deleteByCustomerId(String customerId) throws DAOException {
        return transactionRepository.deleteByCustomerId(customerId);
    }

    @Override
    public Flux<Object> getByCustomerId(String customerId) throws DAOException {
        return transactionRepository.findAllByCustomerId(customerId);
    }

    private String generateTransactionNumber() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "TR-" + String.valueOf(timestamp.getTime()).substring(3);
    }
}
