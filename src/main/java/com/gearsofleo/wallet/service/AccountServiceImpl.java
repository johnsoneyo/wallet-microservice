/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.dataaccessobject.AccountRepository;
import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.exception.WalletException;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author johnson3yo
 * this is an implementation of the account/player service interface
 * since it is the only implementation no qualifier is defined for the service bean
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account accountByPK(Long accountId) throws WalletException {
        return accountRepository.findById(accountId).
                orElseThrow(() -> new WalletException(String.format("account with '%d' not found ", accountId)));
    }

    /**
     * this operations registers a player and creates and account for him/her
     * with minimal details     
     */
    @Override
    @Transactional
    public Account save(Account account) throws WalletException {
        if (account.getPlayerName() != null) {
            if (account.getPlayerName().length() < 5) {
                throw new WalletException("player name is should be 5 characters of more");
            }
            return accountRepository.save(account);
        }
        throw new WalletException("player name is mandatory");
    }

    /**
     * this operation updates a players account information
     * and checks for concurrent player modification
     */
    @Override
    @Transactional
    public Account update(Account account, Long accountId) throws WalletException {
        if (account.getPlayerName() != null) {
            account.setId(accountId);
            try {
                return accountRepository.save(account);
            } catch (ObjectOptimisticLockingFailureException e) {
                throw new WalletException("refresh your page to get updated players");
            }
        }
        throw new WalletException("player name is mandatory");

    }

    /**
     * this operation gets all account lists and their respective 
     * wallet transactions 
     */
    @Override
    public List<Account> getList() {
        return Lists.newArrayList(accountRepository.findAll());
    }

}
