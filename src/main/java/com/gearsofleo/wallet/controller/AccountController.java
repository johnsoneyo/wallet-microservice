/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.controller;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.dataobject.Wallet;
import com.gearsofleo.wallet.datatransferobject.AccountDTO;
import com.gearsofleo.wallet.datatransferobject.WalletDTO;
import com.gearsofleo.wallet.datatransferobject.mapper.AccountMapper;
import com.gearsofleo.wallet.datatransferobject.mapper.WalletMapper;
import com.gearsofleo.wallet.exception.InsufficientBalanceException;
import com.gearsofleo.wallet.exception.WalletException;
import com.gearsofleo.wallet.service.AccountService;
import com.gearsofleo.wallet.service.WalletService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RestController
@RequestMapping("v1/players")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity getPlayers() {
        List<Account> accounts = accountService.getList();
        return new ResponseEntity<List<AccountDTO>>(AccountMapper.doToDTOList(accounts), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlayer(@PathVariable("id") Long id) {
        Account account;
        try {
            account = accountService.accountByPK(id);
        } catch (WalletException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(account), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity createPlayer(@RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.save(AccountMapper.dtoToDO(accountDTO));
        } catch (WalletException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePlayer(@PathVariable("id") Long accountId, @RequestBody AccountDTO accountDTO) {
        Account saved;
        try {
            saved = accountService.update(AccountMapper.dtoToDO(accountDTO), accountId);
        } catch (WalletException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<AccountDTO>(AccountMapper.doToDTO(saved), HttpStatus.OK);
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity createTransaction(@PathVariable("id")Long accountId,@RequestBody WalletDTO walletDTO) {
        Wallet saved;
        try {
            walletDTO.setAccountId(accountId);
            saved = walletService.createTransaction(WalletMapper.dtoToDO(walletDTO));
        } catch (WalletException | InsufficientBalanceException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } 
        return new ResponseEntity<WalletDTO>(WalletMapper.doToDTO(saved), HttpStatus.CREATED);
    }
    
  
   

}
