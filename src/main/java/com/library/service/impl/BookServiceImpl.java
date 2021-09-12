package com.library.service.impl;

import com.library.dao.BookDAO;
import com.library.entity.Book;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

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
