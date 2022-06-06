package com.wraith.netgrif.classes.db;

import com.wraith.netgrif.classes.exceptions.PropertyInsuranceException;
import com.wraith.netgrif.classes.utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "property_insurance")

public class PropertyInsurance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerID;
    private String date;
    private int type;
    private String address;
    private Long price;

    public Long getID() { return id; }
    public void setID(Long id) { this.id = id; }

    public Long getOwnerID() { return ownerID; }
    public void setOwnerID(Long ownerID) { this.ownerID = ownerID; }

    public String getDate() { return date; }
    public void setDate(String date) throws PropertyInsuranceException {

        if(!Utils.controlDate(date))
            throw new PropertyInsuranceException("Invalid date format in property insurance, expected: yyyy.mm.dd");

        this.date = date;
    }

    public int getIntType() { return type; }
    public String getStringType() { return new String[] {"Byt", "Rodinny dom - murovany", "Rodinny dom - dreveny"}[type - 1]; }

    public void setType(int type) throws PropertyInsuranceException
    {
        if(type < 1 || type > 3)
            throw new PropertyInsuranceException("Invalid property type");

        this.type = type;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) throws PropertyInsuranceException {

        if(address == null || address.length() < 3)
            throw new PropertyInsuranceException("Invalid address");

        this.address = address;
    }

    public Long getPrice() { return price; }
    public void setPrice(Long price) throws PropertyInsuranceException
    {
        if(price == null || price <= 0)
            throw new PropertyInsuranceException("Invalid property price");

        this.price = price;
    }
}
