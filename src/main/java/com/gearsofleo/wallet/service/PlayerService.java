/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.service;

import com.gearsofleo.wallet.exception.WalletException;
import java.util.List;

/**
 *
 * @author johnson3yo
 * @param <T>
 * This is a unity interface implemented by all factory interfaces
 * it defines abstract common method operations
 */
public interface PlayerService<T> {
    T save(T t) throws WalletException;
    T update(T t,Long id) throws WalletException ;
    List<T> getList();
}
