package com.library.servicelibrary.controller;

import com.library.servicelibrary.entity.dto.request.LibraryRequest;
import com.library.servicelibrary.entity.dto.response.LibraryResponse;
import com.library.servicelibrary.entity.models.Library;
import com.library.servicelibrary.service.LibraryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {
    final ModelMapper mapper;
    final LibraryService libraryService;

    @Autowired
    public LibraryController(ModelMapper mapper, LibraryService libraryService) {
        this.mapper = mapper;
        this.libraryService = libraryService;
    }

    @QueryMapping
    LibraryResponse findLibrary(@Argument Long id) {
        return mapper.map(
                libraryService.findById(id),
                LibraryResponse.class
        );
    }

    @QueryMapping
    List<LibraryResponse> findLibraries() {
        return libraryService.findAll().stream().map(
                library -> mapper.map(library, LibraryResponse.class)
        ).toList();
    }

    @MutationMapping
    String deleteLibrary(@Argument Long id) {
        libraryService.deleteById(id);
        return "success delete";
    }


    @MutationMapping
    LibraryResponse createLibrary(@Argument LibraryRequest library) {
        LibraryResponse response = mapper.map(
                libraryService.create(mapper.map(
                        library,
                        Library.class)
                ),
                LibraryResponse.class
        );
        response.setBooks(new ArrayList<>());
        return response;
    }

    @MutationMapping
    LibraryResponse updateLibrary(@Argument Long id, @Argument LibraryRequest library) {
        return mapper.map(
                libraryService.update(id, mapper.map(
                        library,
                        Library.class
                )),
                LibraryResponse.class
        );
    }

    @MutationMapping
    String addBookInLibrary(@Argument Long libraryId, @Argument Long bookId) {
        return libraryService.addBook(libraryId, bookId);
    }


}
