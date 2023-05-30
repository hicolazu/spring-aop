package com.americanas.springaop.aspect;

import com.americanas.springaop.model.Account;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Aspect
public class AccountAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AccountAspect.class);

    @Pointcut("execution(* com.americanas.springaop.service.AccountService.saveAccount(..)) && args(account)")
    private void onAccountSave(Account account) {}

    @Pointcut("execution(* com.americanas.springaop.service.AccountService.findAllAccounts(..))")
    private void onAccountsFind() {}

    @Before("onAccountSave(account)")
    public void beforeAccountSave(Account account) {
        LOG.info("Creating new account with name {} and email {}.", account.getName(), account.getEmail());
    }

    @AfterReturning(value = "onAccountSave(account)", argNames = "account")
    public void afterAccountSave(Account account) {
        LOG.info("New account {} created.", account.getEmail());

        // poderia, por exemplo, salvar um log de auditoria no BigQuery
    }

    @AfterThrowing(value = "onAccountSave(account)", argNames = "account")
    public void afterThrowingAccountSave(Account account) {
        LOG.info("Error creating new account.");

        if (account.getName() == null || account.getName().isEmpty()) {
            LOG.error("Account name is required.");
        }

        if (account.getEmail() == null || account.getEmail().isEmpty()) {
            LOG.error("Account email is required.");
        }

        // poderia, por exemplo, enviar para uma DLQ ou fazer um retry
    }

    @AfterReturning(pointcut = "onAccountsFind()", returning = "accounts")
    public void afterAccountsFind(Set<Account> accounts) {
        LOG.info("Found {} accounts.", accounts.size());
    }
}
