package com.library.service.impl;

import com.library.dao.RentInfoDAO;
import com.library.entity.RentInfo;
import com.library.entity.RentStatus;
import com.library.service.RentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RentInfoServiceImpl implements RentInfoService {

    private final RentInfoDAO rentInfoDAO;

    @Override
    public RentInfo save(RentInfo rentInfo) {
        return rentInfoDAO.save(rentInfo);
    }

    @Override
    public RentInfo findById(Long id) {
        return rentInfoDAO.findById(id);
    }

    @Override
    public List<RentInfo> findAll() {
        return rentInfoDAO.findAll();
    }

    @Override
    public List<RentInfo> findByRentStatus(RentStatus rentStatus) {
        return rentInfoDAO.findByRentStatus(rentStatus);
    }


    @Override
    public RentInfo remove(Long id) {
        return rentInfoDAO.remove(id);
    }

    @Override
    public List<RentInfo> findByUserId(Long id) {
        return rentInfoDAO.findByUserId(id);
    }

    @Override
    public RentInfo update(RentInfo rentInfo) {
        return rentInfoDAO.update(rentInfo);
    }

    @Override
    public void updateDebtors() {
     rentInfoDAO.updateDebtors();
    }
}
