package com.wraith.netgrif.classes.services;

import com.wraith.netgrif.classes.db.PropertyInsurance;
import com.wraith.netgrif.interfaces.InsuranceInterface;
import com.wraith.netgrif.interfaces.repository.PropertyInsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyInsuranceQueryService implements InsuranceInterface<PropertyInsurance>
{
    private final PropertyInsuranceRepository propertyInsuranceRepository;
    public PropertyInsuranceQueryService(PropertyInsuranceRepository propertyInsuranceRepository) { this.propertyInsuranceRepository = propertyInsuranceRepository; }

    @Override
    public long createInsurance(PropertyInsurance insurance) { return propertyInsuranceRepository.save(insurance).getID(); }

    @Override
    public List<PropertyInsurance> getInsuranceByOwnerID(long OwnerID) { return propertyInsuranceRepository.findByOwnerID(OwnerID); }
}
