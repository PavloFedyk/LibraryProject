package com.library.dao.impl;


import com.library.entity.Role;
import com.library.dao.RoleDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {

    private final SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public Role findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Role.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select r from Role r", Role.class)
                .getResultList();
    }


}