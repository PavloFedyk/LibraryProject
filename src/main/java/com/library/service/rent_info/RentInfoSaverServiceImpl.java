package com.library.service.rent_info;

import com.library.dao.RentInfoDAO;
import com.library.entity.RentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentInfoSaverServiceImpl implements RentInfoSaverService {
    private final RentInfoDAO rentInfoDAO;

    @Override
    public RentInfo save(RentInfo rentInfo) {
        return rentInfoDAO.save(rentInfo);
    }
}
