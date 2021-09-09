package com.library.service;

import com.library.entity.RentInfo;
import com.library.entity.RentStatus;

import java.util.List;

public interface RentInfoService {

    RentInfo save(RentInfo rentInfo);

    RentInfo findById(Long id);

    List<RentInfo> findByRentStatus(RentStatus rentStatus);

    List<RentInfo> findAll(RentInfo rentInfo);

    RentInfo remove(Long id);

    List<RentInfo> findByUserId(Long id);

    RentInfo update(RentInfo rentInfo);

    void updateDebtors();


    List<RentInfo> findAll();
}
