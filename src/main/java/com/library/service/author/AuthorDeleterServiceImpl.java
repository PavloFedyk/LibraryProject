package com.library.service.author;

import com.library.dao.AuthorDAO;
import com.library.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorDeleterServiceImpl implements AuthorDeleterService {

    private final AuthorDAO authorDAO;

    @Override
    public Author remove(final Long id) {
        return authorDAO.remove(id);
    }
}
