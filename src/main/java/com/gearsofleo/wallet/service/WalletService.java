/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.dataobject.Wallet;
import com.gearsofleo.wallet.exception.InsufficientBalanceException;
import com.gearsofleo.wallet.exception.WalletException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author johnson3yo
 */
public interface WalletService extends PlayerService<Wallet>{
    List<Wallet> transactionsByAccountID(Long accountId)  throws WalletException;
    Wallet transactionByRef(Long txnRef)  throws WalletException;
    /**
     *
     * @param accountId
     * @return
     * @throws com.gearsofleo.wallet.exception.WalletException
     */
    BigDecimal balanceByAccountID(Long accountId)  throws WalletException;
    List<Wallet> transactions();
    Wallet createTransaction(Wallet wallet) throws WalletException,InsufficientBalanceException;
}
