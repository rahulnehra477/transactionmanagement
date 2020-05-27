package com.example.transactionmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Transaction")
public class Transaction {

    @Id
    private String id;
    @NotNull(message = "User name can not be null")
    private String customerId;

    @NotNull(message = "Sender Account can not be null")
    private String senderAccount;

    @NotNull(message = "Receiver Account can not be null")
    private String receiverAccount;

    private long amount;

    private String comment;

    private String transactionRefNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTransactionRefNum() {
        return transactionRefNum;
    }

    public void setTransactionRefNum(String transactionRefNum) {
        this.transactionRefNum = transactionRefNum;
    }
}
