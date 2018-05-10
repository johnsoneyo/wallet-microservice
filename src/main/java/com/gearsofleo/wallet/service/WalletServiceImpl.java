/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.dataaccessobject.WalletRepository;
import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.dataobject.Wallet;
import com.gearsofleo.wallet.exception.InsufficientBalanceException;
import com.gearsofleo.wallet.exception.WalletException;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author johnson3yo this is an implementation of the wallet service interface
 * since it is the only implementation no qualifier is defined for the service
 * bean
 */
@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private AccountService accountService;

    /**
     * retrieve transactions by their transaction reference this operations is
     * used to validate if a transaction ref has been used previously
     */
    @Override
    public Wallet transactionByRef(Long txnRef) throws WalletException {
        return walletRepository.getTransactionByRef(txnRef).
                orElseThrow(() -> new WalletException(String.format("transaction with ref '%d' doesnt exist", txnRef)));
    }

    /**
     * this operations registers a transaction on behalf of player
     * debit/credits, it also validates if a player has insufficient funds if a
     * debit is to be made
     */
    @Override
    @Transactional
    public Wallet createTransaction(Wallet wallet) throws WalletException, InsufficientBalanceException {
        // checks for transaction ref to ensure its uniqueness
        if (walletRepository.getTransactionByRef(wallet.getTransactionReference()).isPresent()) {
            throw new WalletException("transaction ref has been used ");
        }
        BigDecimal balance = walletRepository.getBalance(wallet.getAccount().getId());

        if (balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 1
                | balance.add(wallet.getAmount()).compareTo(BigDecimal.ZERO) == 0) {
            return walletRepository.save(wallet);
        }

        throw new InsufficientBalanceException(String.format("player's balance is %.2f and cannot perform a transaction of %.2f ",
                balance.doubleValue(), wallet.getAmount().doubleValue()));

    }

    @Override
    public Wallet update(Wallet wallet, Long id) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Wallet> getList() {
        return Lists.newArrayList(walletRepository.findAll());
    }

    @Override
    public List<Wallet> transactionsByAccountID(Long accountId) {
        return walletRepository.getTransactionsForPlayer(accountId);
    }

    @Override
    public BigDecimal balanceByAccountID(Long accountId) {
        return walletRepository.getBalance(accountId);
    }

    @Override
    public List<Wallet> transactions() {
        return Lists.newArrayList(walletRepository.findAll());
    }

    @Override
    public Wallet save(Wallet wallet) throws WalletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
