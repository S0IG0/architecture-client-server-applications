package com.library.servicelibrary.controller;

import com.library.servicelibrary.entity.dto.request.BookRequest;
import com.library.servicelibrary.entity.dto.response.BookResponse;
import com.library.servicelibrary.entity.models.Author;
import com.library.servicelibrary.entity.models.Book;
import com.library.servicelibrary.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    final ModelMapper mapper;
    final BookService bookService;

    @Autowired
    public BookController(ModelMapper mapper, BookService bookService) {
        this.mapper = mapper;
        this.bookService = bookService;
    }

    @QueryMapping
    BookResponse findBook(@Argument Long id) {
        return mapper.map(
                bookService.findById(id),
                BookResponse.class
        );
    }

    @QueryMapping
    List<BookResponse> findBooks() {
        return bookService.findAll().stream().map(
                book -> mapper.map(book, BookResponse.class)
        ).toList();
    }

    @MutationMapping
    String deleteBook(@Argument Long id) {
        bookService.deleteById(id);
        return "success delete";
    }

    @MutationMapping
    BookResponse createBook(@Argument BookRequest book) {
        Book mapped = mapper.map(
                book,
                Book.class
        );
        mapped.setAuthors(book.getAuthors().stream().map(
                id -> (Author) Author.builder()
                        .id(id)
                        .build()
        ).toList());


        return mapper.map(
                bookService.findById(bookService.create(mapped).getId()),
                BookResponse.class
        );
    }

    @MutationMapping
    BookResponse updateBook(@Argument Long id, @Argument BookRequest book) {
        Book mapped = mapper.map(
                book,
                Book.class
        );
        mapped.setAuthors(book.getAuthors().stream().map(
                bookId -> (Author) Author.builder()
                        .id(bookId)
                        .build()
        ).toList());

        return mapper.map(
                bookService.update(id, mapped),
                BookResponse.class
        );
    }
}


