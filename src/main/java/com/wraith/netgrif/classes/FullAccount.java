package com.wraith.netgrif.classes;

import com.wraith.netgrif.classes.db.Account;
import com.wraith.netgrif.classes.db.PropertyInsurance;
import com.wraith.netgrif.classes.db.TravelInsurance;

import java.util.List;

public class FullAccount
{
    private Account account;
    private List<TravelInsurance> travelInsurance;
    private List<PropertyInsurance> propertyInsurance;

    public FullAccount(Account account, List<TravelInsurance> travelInsurance, List<PropertyInsurance> propertyInsurance)
    {
        this.account = account;
        this.travelInsurance = travelInsurance;
        this.propertyInsurance = propertyInsurance;

    }

    public Account getAccount() { return account; }
    public List<TravelInsurance> getTravelInsurance() { return travelInsurance; }
    public List<PropertyInsurance> getPropertyInsurance() { return propertyInsurance; }

    public void setAccount(Account account) { this.account = account; }
    public void setTravelInsurance(List<TravelInsurance> travelInsurance) { this.travelInsurance = travelInsurance; }
    public void setPropertyInsurance(List<PropertyInsurance> propertyInsurance) { this.propertyInsurance = propertyInsurance; }
}
