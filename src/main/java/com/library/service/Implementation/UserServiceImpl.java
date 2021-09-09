package com.library.service.Implementation;

import com.library.DAO.UserDAO;
import com.library.entity.RentStatus;
import com.library.entity.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserDAO {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByIdFetchRentInfo(Long id) {
        return userDAO.findByIdFetchRentInfo(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User remove(Long id) {
        return userDAO.remove(id);
    }

    @Override
    public User getUserByUsername(String username) {
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
