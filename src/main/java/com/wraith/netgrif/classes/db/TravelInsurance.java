package com.wraith.netgrif.classes.db;

import com.wraith.netgrif.classes.exceptions.TravelInsuranceException;
import com.wraith.netgrif.classes.utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "travel_insurance")

public class TravelInsurance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long ownerID;
    private String dateStart;
    private String dateEnd;
    private boolean damageLiability;
    private boolean used;

    public Long getID() { return id; }
    public void setID(Long id) { this.id = id; }

    public Long getOwnerID() { return ownerID; }
    public void setOwnerID(Long ownerID) throws TravelInsuranceException {

        if(ownerID < 1)
            throw new TravelInsuranceException("Invalid owner ID");

        this.ownerID = ownerID;
    }

    public String getDateStart() { return dateStart; }
    public void setDateStart(String dateStart) throws TravelInsuranceException {

        if(!Utils.controlDate(dateStart))
            throw new TravelInsuranceException("Invalid starting date format in property insurance, expected: yyyy.mm.dd");

        this.dateStart = dateStart;
    }

    public String getDateEnd() { return dateEnd; }
    public void setDateEnd(String dateEnd) throws TravelInsuranceException {

        if(!Utils.controlDate(dateStart))
            throw new TravelInsuranceException("Invalid starting date format in property insurance, expected: yyyy.mm.dd");

        this.dateEnd = dateEnd;
    }

    public boolean isDamageLiability() { return damageLiability; }
    public void setDamageLiability(boolean damageLiability) { this.damageLiability = damageLiability; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
}
