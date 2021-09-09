package com.library.DAO.Implementation;

import com.library.DAO.AuthorDAO;
import com.library.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class AuthorDAOImpl implements AuthorDAO {

    private final SessionFactory sessionFactory;

    public AuthorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Author save(Author author) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(author);

        return author;
    }

    @Override
    public Author findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.setDefaultReadOnly(true);

        return session.get(Author.class, id);
    }

    @Override
    public Author findByIdFetchBooks(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.setDefaultReadOnly(true);

        return session.createQuery("select a from Author a left join fetch a.books left join fetch a.coAuthorBooks where a.id=:id", Author.class)
                .setParameter("id",id)
                .getSingleResult()
                ;
    }

    @Override
    public List<Author> findAll() {
        Session session = sessionFactory.getCurrentSession();
        session.setDefaultReadOnly(true);

        return session.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author remove(Long id) {
        Session session = sessionFactory.getCurrentSession();

        Author author = findById(id);

        session.remove(author);

        return author;
    }
}
