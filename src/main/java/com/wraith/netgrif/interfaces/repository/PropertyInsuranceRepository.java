package com.wraith.netgrif.interfaces.repository;

import com.wraith.netgrif.classes.db.PropertyInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyInsuranceRepository extends JpaRepository<PropertyInsurance, Long>
{
    List<PropertyInsurance> findByOwnerID(long OwnerID);
}
