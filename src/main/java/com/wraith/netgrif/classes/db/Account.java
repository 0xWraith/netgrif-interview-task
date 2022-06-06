package com.wraith.netgrif.classes.db;

import com.wraith.netgrif.classes.exceptions.CreateAccountException;
import com.wraith.netgrif.classes.utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "account")

public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String identNumber;
    private String mail;
    private String address;
    private String corrAddress;

    public Long getID() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getIdentNumber() { return identNumber; }
    public String getMail() { return mail; }
    public String getAddress() { return address; }
    public String getCorrAddress() { return (corrAddress == null ? address : corrAddress); }

    public void setID(Long id) { this.id = id; }

    public void setFirstName(String firstName) throws CreateAccountException
    {
        if(firstName == null || firstName.length() < 2)
            throw new CreateAccountException("Invalid first name");

        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws CreateAccountException {

        if(lastName == null || lastName.length() < 2)
            throw new CreateAccountException("Invalid last name");

        this.lastName = lastName;
    }

    public void setIdentNumber(String identNumber) throws CreateAccountException {

        if(!Utils.controlIdentificationNumber(identNumber))
            throw new CreateAccountException("Invalid identification number");

        this.identNumber = identNumber;
    }

    public void setMail(String mail) throws CreateAccountException {

        if(mail == null || mail.length() < 5 || !mail.contains("@") || !mail.contains("."))
            throw new CreateAccountException("Invalid mail");

        this.mail = mail;
    }

    public void setAddress(String address) throws CreateAccountException {

        if(address == null || !Utils.controlAddress(address))
            throw new CreateAccountException("Invalid address");

        this.address = address;
    }

    public void setCorrAddress(String corrAddress) throws CreateAccountException {

        if(corrAddress == null)
            return;

        if(!Utils.controlAddress(corrAddress))
            throw new CreateAccountException("Invalid address");

        this.corrAddress = corrAddress;
    }
}
