package com.example.transactionmanagement.Controller;

import com.example.transactionmanagement.Dao.exception.DAOException;
import com.example.transactionmanagement.Model.Transaction;
import com.example.transactionmanagement.Model.TransactionDTO;
import com.example.transactionmanagement.Services.ITransactionService;
import com.example.transactionmanagement.Services.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction/")
public class TransactionManageController {

    @Autowired
    ITransactionService iTransactionService;


    @PostMapping(value = "/create")
    Mono<ResponseEntity<TransactionDTO>> createAccount(@Valid @RequestBody Transaction transaction) throws ServiceException {
        return iTransactionService.validateAndUpdateAccount(transaction).filter(obj->obj.booleanValue())
               .flatMap( obj-> {
                    try {
                        return iTransactionService.createTransaction(transaction);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .map(obj->{
                    TransactionDTO transactionDTO=new TransactionDTO();
                    transactionDTO.setTransactionRefNum(obj.getTransactionRefNum());
                    transactionDTO.setStatus(true);
                    return transactionDTO;
                })
                .map(transactionDTO -> new ResponseEntity<>(transactionDTO,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/get/{customerId}")
    ResponseEntity<Flux<Object>> getByCustomerId(@PathVariable String customerId) throws DAOException {
        return new ResponseEntity<>(iTransactionService.getByCustomerId(customerId),HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{customerId}")
    ResponseEntity<Flux<Object>> deleteByCustomerId(@PathVariable String customerId) throws DAOException {
        return new ResponseEntity<>(iTransactionService.deleteByCustomerId(customerId),HttpStatus.OK);
    }
}
