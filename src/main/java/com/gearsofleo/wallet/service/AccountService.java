/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.exception.WalletException;

/**
 *
 * @author johnson3yo
 */
public interface AccountService extends  PlayerService<Account> {
   Account accountByPK(Long accountId) throws WalletException;
}
