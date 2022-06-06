package com.wraith.netgrif.classes.services;

import com.wraith.netgrif.classes.db.Account;
import com.wraith.netgrif.interfaces.repository.AccountRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryService
{
    private final AccountRepository accountRepository;
    public AccountQueryService(AccountRepository accountRepository) { this.accountRepository = accountRepository; }

    public Account getAccountByMail(String mail) { return accountRepository.findAccountByMail(mail); }
    public Optional<Account> getAccountByID(long id) { return accountRepository.findById(id); }
    public Long createAccount(Account account) { return accountRepository.save(account).getID(); }
    public List<Account> getAccounts() { return accountRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName")); }
}
