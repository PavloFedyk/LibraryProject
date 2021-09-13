package com.library.service.rent_info;

import com.library.dao.RentInfoDAO;
import com.library.entity.RentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentInfoDeleterServiceImpl implements RentInfoDeleterService {

    private final RentInfoDAO rentInfoDAO;

    @Override
    public RentInfo remove(Long id) {
        return rentInfoDAO.remove(id);
    }
}
