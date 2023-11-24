package com.library.servicelibrary.controller;

import com.library.servicelibrary.entity.dto.request.AuthorRequest;
import com.library.servicelibrary.entity.dto.response.AuthorResponse;
import com.library.servicelibrary.entity.models.Author;
import com.library.servicelibrary.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {
    final AuthorService authorService;
    final ModelMapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @QueryMapping
    List<AuthorResponse> findAuthors() {
        return authorService.findAll().stream().map(
                author -> mapper.map(author, AuthorResponse.class)
        ).toList();
    }

    @QueryMapping
    AuthorResponse findAuthor(@Argument Long id) {
        return mapper.map(
                authorService.findById(id),
                AuthorResponse.class
        );
    }

    @MutationMapping
    AuthorResponse createAuthor(@Argument AuthorRequest author) {
        return mapper.map(
                authorService.create(mapper.map(
                        author,
                        Author.class
                )),
                AuthorResponse.class
        );
    }

    @MutationMapping
    AuthorResponse updateAuthor(@Argument Long id, @Argument AuthorRequest author) {
        return mapper.map(
                authorService.update(id, mapper.map(
                        author,
                        Author.class
                )),
                AuthorResponse.class
        );
    }

    @MutationMapping
    String deleteAuthor(@Argument Long id) {
        authorService.deleteById(id);
        return "success delete";
    }
}
