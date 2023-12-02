package com.library.servicelibrary.service.impl;

import com.library.servicelibrary.entity.models.Book;
import com.library.servicelibrary.entity.models.Library;
import com.library.servicelibrary.exception.NotFoundItemException;
import com.library.servicelibrary.repository.LibraryRepository;
import com.library.servicelibrary.service.BookService;
import com.library.servicelibrary.service.LibraryService;
import com.library.servicelibrary.service.base.impl.BaseCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl extends BaseCRUDServiceImpl<Library, Long> implements LibraryService {
    final BookService bookService;
    @Autowired
    public LibraryServiceImpl(LibraryRepository repository, BookService bookService) {
        super(repository);
        this.bookService = bookService;
    }

    @Override
    public String addBook(Long libraryId, Long bookId) {
        Library library = findById(libraryId);
        Book book = bookService.findById(bookId);
        List<Library> libraries = book.getLibraries();
        libraries.add(library);
        try {
            bookService.create(book);
        } catch (Exception exception) {
            throw new NotFoundItemException("not found book");
        }
        return "success add new book in library";
    }
}
