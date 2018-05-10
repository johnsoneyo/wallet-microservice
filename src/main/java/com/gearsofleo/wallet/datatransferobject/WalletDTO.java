/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.datatransferobject;

import com.gearsofleo.wallet.dataobject.Account;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author johnson3yo
 */
public class WalletDTO {

    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String purpose;
    private Date transactionDate;
    private Long transactionReference;

    public WalletDTO() {
    }

    public WalletDTO(WalletDTOBuilder builder) {
        id = builder.id;
        accountId = builder.accountId;
        amount = builder.amount;
        purpose = builder.purpose;
        transactionDate = builder.transactionDate;
        transactionReference = builder.transactionReference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(Long transactionReference) {
        this.transactionReference = transactionReference;
    }

    public static class WalletDTOBuilder {

        private Long id;
        private Long accountId;
        private BigDecimal amount;
        private String purpose;
        private Date transactionDate;
        private Long transactionReference;

        public WalletDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public WalletDTOBuilder setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public WalletDTOBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public WalletDTOBuilder setPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public WalletDTOBuilder setTransactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public WalletDTOBuilder setTransactionReference(Long transactionReference) {
            this.transactionReference = transactionReference;
            return this;
        }

        public WalletDTO build() {
            return new WalletDTO(this);
        }

    }

}
