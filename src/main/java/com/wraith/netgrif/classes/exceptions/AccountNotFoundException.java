package com.wraith.netgrif.classes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested user doesn't exist")
public class AccountNotFoundException extends RuntimeException
{
    public AccountNotFoundException(String throwable) { super(throwable); }
}
