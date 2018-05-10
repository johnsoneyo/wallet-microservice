/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.dataaccessobject;

import com.gearsofleo.wallet.dataobject.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author johnson3yo
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> getByPlayerName(String name);
}
