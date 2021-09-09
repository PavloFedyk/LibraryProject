package com.library.service.Implementation;

import com.library.DAO.BookDAO;
import com.library.entity.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookDAO {
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public Book save(Book book) {
        return bookDAO.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public Book findByIdFetchCoAuthors(Long id) {
        return bookDAO.findByIdFetchCoAuthors(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book remove(Long id) {
        return bookDAO.remove(id);
    }

    @Override
    public List<Book> findBooksByAuthorAndTitle(String str) {
        return bookDAO.findBooksByAuthorAndTitle(str);
    }

    @Override
    public List<Book> findMostPopularBook(LocalDateTime from, LocalDateTime to) {
        return bookDAO.findMostPopularBook(from,to);
    }

    @Override
    public List<Book> findMostUnpopularBook(LocalDateTime from, LocalDateTime to) {
        return findMostUnpopularBook(from,to);
    }

    @Override
    public Optional<Double> averageTimeOfReadingBook(String title) {
        return bookDAO.averageTimeOfReadingBook(title);
    }
}
