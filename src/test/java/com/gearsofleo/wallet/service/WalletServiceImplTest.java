/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.dataobject.Wallet;
import com.gearsofleo.wallet.exception.InsufficientBalanceException;
import com.gearsofleo.wallet.exception.WalletException;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author johnson3yo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceImplTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;

    private Account account;

    @Before
    public void setupPlayerAccount() throws WalletException {
        account = accountService.save(new Account("eric cartman", "M", new Date()));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void registerDebitTransactionof5000ForEricCartman() throws WalletException, InsufficientBalanceException {
        Wallet wallet = new Wallet(account, new BigDecimal(-5000), "debit", new Date(), 150L);
        walletService.createTransaction(wallet);
    }

    @Test
    public void registerCreditTransactionof10000ForEricCartman() throws WalletException, InsufficientBalanceException {
        Wallet saved1 = new Wallet(account, new BigDecimal(10000), "credit", new Date(), 150L);
        Wallet savedTransaction1 = walletService.createTransaction(saved1);
        assertNotNull(savedTransaction1);
        BigDecimal balance = walletService.balanceByAccountID(account.getId());
        assertTrue(balance.doubleValue() == 10000);
    }

}
