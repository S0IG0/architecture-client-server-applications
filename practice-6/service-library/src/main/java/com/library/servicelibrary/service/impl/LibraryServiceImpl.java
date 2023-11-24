package com.library.servicelibrary.service.impl;

import com.library.servicelibrary.entity.models.Book;
import com.library.servicelibrary.entity.models.Library;
import com.library.servicelibrary.exception.NotFoundItemException;
import com.library.servicelibrary.repository.LibraryRepository;
import com.library.servicelibrary.service.LibraryService;
import com.library.servicelibrary.service.base.impl.BaseCRUDServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl extends BaseCRUDServiceImpl<Library, Long> implements LibraryService {
    @Autowired
    public LibraryServiceImpl(LibraryRepository repository) {
        super(repository);
    }

    @Override
    public String addBook(Long libraryId, Long bookId) {
        Library library = findById(libraryId);
        Book book = Book.builder()
                .id(bookId)
                .build();
        List<Book> books = library.getBooks();
        books.add(book);
        library.setBooks(books);
        try {
            create(library);
        } catch (Exception exception) {
            throw new NotFoundItemException("not found book");
        }
        return "success add new book in library";
    }
}
