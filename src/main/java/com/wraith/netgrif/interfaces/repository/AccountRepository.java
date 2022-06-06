package com.wraith.netgrif.interfaces.repository;

import com.wraith.netgrif.classes.db.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    Account findAccountByMail(String mail);
}
