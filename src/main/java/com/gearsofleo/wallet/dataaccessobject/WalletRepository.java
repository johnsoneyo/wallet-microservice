/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.dataaccessobject;

import com.gearsofleo.wallet.dataobject.Wallet;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author johnson3yo
 */
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    @Query(nativeQuery = true, value = "select * from wallet where transaction_reference = ?")
    Optional<Wallet> getTransactionByRef(Long txnRef);

    @Query(nativeQuery = true, value = "select ifnull(sum(amount),0.00) from wallet where account_id = ?")
    BigDecimal getBalance(Long accountId);

    @Query(nativeQuery = true, value = "select * from wallet where account_id = ?")
    List<Wallet> getTransactionsForPlayer(Long accountId);
    
}
