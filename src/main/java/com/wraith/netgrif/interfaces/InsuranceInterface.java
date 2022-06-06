package com.wraith.netgrif.interfaces;

import java.util.List;

public interface InsuranceInterface<I>
{
    long createInsurance(I insurance);
    List<I> getInsuranceByOwnerID(long ownerID);
}
