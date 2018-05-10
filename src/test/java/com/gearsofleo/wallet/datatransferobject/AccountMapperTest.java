/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.datatransferobject;

import com.gearsofleo.wallet.dataobject.Account;
import com.gearsofleo.wallet.datatransferobject.mapper.AccountMapper;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author johnson3yo
 */
public class AccountMapperTest {

    private static Account account;
    private static AccountDTO accountDTO;

    @Before
    public void setupAccountObject() {
        account = new Account(1L, "sponge bob", "F", new Date());
    }

    @Test
    public void testDataObjectToDataTransferObject() {
        accountDTO = AccountMapper.doToDTO(account);
        Assert.assertEquals(accountDTO.getId(), account.getId());
        Assert.assertEquals(accountDTO.getPlayerName(), account.getPlayerName());
        Assert.assertEquals(accountDTO.getSex(), account.getSex());

    }

    @Test
    public void testDataTransferObjectToDataObject() {
        account = AccountMapper.dtoToDO(accountDTO);
        Assert.assertEquals(account.getId(), accountDTO.getId());
        Assert.assertEquals(account.getPlayerName(), accountDTO.getPlayerName());
        Assert.assertEquals(account.getSex(), accountDTO.getSex());

    }
}
