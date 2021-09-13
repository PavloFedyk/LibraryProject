package com.library.service.book;

import com.library.dao.BookDAO;
import com.library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRetrieverServiceImpl implements BookRetrieverService{

    private final BookDAO bookDAO;

    @Override
    public Book findById(final Long id) {
        return bookDAO.findById(id);
    }

    @Override
    public Book findByIdFetchCoAuthors(final Long id) {
        return bookDAO.findByIdFetchCoAuthors(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
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
        return bookDAO.findMostUnpopularBook(from,to);
    }

    @Override
    public Optional<Double> averageTimeOfReadingBook(String title) {
        return bookDAO.averageTimeOfReadingBook(title);
    }
}
