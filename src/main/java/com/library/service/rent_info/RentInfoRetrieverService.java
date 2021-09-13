package com.library.service.rent_info;

import com.library.entity.RentInfo;
import com.library.entity.RentStatus;

import java.util.List;

public interface RentInfoRetrieverService {
    RentInfo findById(Long id);

    List<RentInfo> findByRentStatus(RentStatus rentStatus);

    List<RentInfo> findByUserId(Long id);

    RentInfo update(RentInfo rentInfo);

    void updateDebtors();

    List<RentInfo> findAll();
}
