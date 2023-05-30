package com.americanas.springaop.repository;

import com.americanas.springaop.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AccountRepository {

    private Set<Account> accounts = new HashSet<>();

    public void saveAccount(final Account account) throws Exception {
        if (
                account.getName() == null || account.getName().isEmpty()
                ||
                account.getEmail() == null || account.getEmail().isEmpty()
        ) {
            throw new Exception("Account name cannot be null or empty");
        }

        accounts.add(account);
    }

    public Set<Account> findAllAccounts() {
        return accounts;
    }
}
