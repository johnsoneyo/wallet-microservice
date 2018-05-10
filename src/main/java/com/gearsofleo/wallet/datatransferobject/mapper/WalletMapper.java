/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.datatransferobject.mapper;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.dataobject.Wallet;
import com.gearsofleo.wallet.datatransferobject.AccountDTO;
import com.gearsofleo.wallet.datatransferobject.WalletDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author johnson3yo
 */
public class WalletMapper {

    public static Wallet dtoToDO(WalletDTO w) {
        Wallet wallet = new Wallet.WalletBuilder()
                .setAccount(w.getAccountId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setPurpose(w.getPurpose())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return wallet;
    }

    public static WalletDTO doToDTO(Wallet w) {
        WalletDTO walletDTO = new WalletDTO.WalletDTOBuilder()
                .setAccountId(w.getAccount().getId())
                .setAmount(w.getAmount())
                .setId(w.getId())
                .setPurpose(w.getPurpose())
                .setTransactionDate(w.getTransactionDate())
                .setTransactionReference(w.getTransactionReference()).build();
        return walletDTO;
    }

    public static List<WalletDTO> doToDTOList(List<Wallet> txns) {
        return txns.stream()
                .map(WalletMapper::doToDTO)
                .collect(Collectors.toList());
    }
}
