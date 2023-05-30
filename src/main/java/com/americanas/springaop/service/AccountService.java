package com.americanas.springaop.service;

import com.americanas.springaop.model.Account;
import com.americanas.springaop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void saveAccount(final Account account) throws Exception {
        accountRepository.saveAccount(account);
    }

    public Set<Account> findAllAccounts() {
        return accountRepository.findAllAccounts();
    }
}
