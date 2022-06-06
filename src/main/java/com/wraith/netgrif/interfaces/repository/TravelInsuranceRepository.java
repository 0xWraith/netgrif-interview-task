package com.wraith.netgrif.interfaces.repository;

import com.wraith.netgrif.classes.db.TravelInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelInsuranceRepository extends JpaRepository<TravelInsurance, Long>
{
    List<TravelInsurance> findByOwnerID(long OwnerID);
}
