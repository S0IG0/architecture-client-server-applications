package com.library.servicelibrary.service.impl;

import com.library.servicelibrary.entity.models.Author;
import com.library.servicelibrary.entity.models.Book;
import com.library.servicelibrary.exception.NotFoundItemException;
import com.library.servicelibrary.repository.BookRepository;
import com.library.servicelibrary.service.AuthorService;
import com.library.servicelibrary.service.BookService;
import com.library.servicelibrary.service.base.impl.BaseCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends BaseCRUDServiceImpl<Book, Long> implements BookService {
    final BookRepository repository;
    final AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository repository, AuthorService authorService) {
        super(repository);
        this.repository = repository;
        this.authorService = authorService;
    }

    @Override
    public Book create(Book item) {
        try {
            item = this.repository.saveAndFlush(item);
        } catch (Exception exception) {
            throw new NotFoundItemException("not found authors");
        }
        repository.clear();
        return super.findById(item.getId());
    }
}
