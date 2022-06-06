package com.wraith.netgrif.classes.services;

import com.wraith.netgrif.classes.db.TravelInsurance;
import com.wraith.netgrif.interfaces.InsuranceInterface;
import com.wraith.netgrif.interfaces.repository.TravelInsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelInsuranceQueryService implements InsuranceInterface<TravelInsurance>
{
    private final TravelInsuranceRepository travelInsuranceRepository;
    public TravelInsuranceQueryService(TravelInsuranceRepository travelInsuranceRepository) { this.travelInsuranceRepository = travelInsuranceRepository; }

    @Override
    public long createInsurance(TravelInsurance insurance) { return travelInsuranceRepository.save(insurance).getID(); }

    @Override
    public List<TravelInsurance> getInsuranceByOwnerID(long OwnerID) { return travelInsuranceRepository.findByOwnerID(OwnerID); }
}
