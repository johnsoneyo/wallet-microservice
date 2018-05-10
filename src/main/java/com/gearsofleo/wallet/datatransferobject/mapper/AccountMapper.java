/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.datatransferobject.mapper;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.datatransferobject.AccountDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author johnson3yo
 */
public class AccountMapper {

    public static Account dtoToDO(AccountDTO a) {
        Account account = new Account.AccountBuilder()
                .setDateCreated(new Date())
                .setId(a.getId())
                .setPlayerName(a.getPlayerName())
                .setSex(a.getSex())
                .build();
        return account;
    }

    public static AccountDTO doToDTO(Account a) {
        double balance = a.getTransactions().stream().mapToDouble(o -> o.getAmount().doubleValue()).sum();
        AccountDTO dto = new AccountDTO.AccountDTOBuilder().setId(a.getId())
                .setDateCreated(a.getDateCreated())
                .setPlayerName(a.getPlayerName())
                .setId(a.getId())
                .setSex(a.getSex())
                .setBalance(new BigDecimal(balance))
                .build();
        return dto;
    }

    public static List<AccountDTO> doToDTOList(List<Account> account) {
        return account.stream()
                .map(AccountMapper::doToDTO)
                .collect(Collectors.toList());
    }

}
