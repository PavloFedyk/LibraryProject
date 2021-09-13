package com.library.service.user;

import com.library.dao.UserDAO;
import com.library.entity.RentStatus;
import com.library.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRetrieverServiceImpl implements UserRetrieverService {
    private final UserDAO userDAO;

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByIdFetchRentInfo(Long id) {
        return userDAO.findByIdFetchRentInfo(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public Integer daysOurClient(Long id) {
        return userDAO.daysOurClient(id);
    }

    @Override
    public Integer amountReadBooks(Long id) {
        return userDAO.amountReadBooks(id);
    }

    @Override
    public Optional<Double> readingTimeOfBooks(RentStatus rentStatus, Long id) {
        return userDAO.readingTimeOfBooks(rentStatus, id);
    }

    @Override
    public Integer averageAge() {
        return userDAO.averageAge();
    }

    @Override
    public Integer averageTimeWorkingWithLibrary() {
        return userDAO.averageTimeWorkingWithLibrary();
    }

    @Override
    public List<User> findAllWithExpiredStatus() {
        return userDAO.findAllWithExpiredStatus();
    }
}
