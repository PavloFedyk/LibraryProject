package com.library.service.user;

import com.library.dao.UserDAO;
import com.library.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSeverServiceImpl implements UserSeverService{
    private final UserDAO userDAO;

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }
}
