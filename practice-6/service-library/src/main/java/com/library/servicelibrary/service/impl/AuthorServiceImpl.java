package com.library.servicelibrary.service.impl;

import com.library.servicelibrary.entity.models.Author;
import com.library.servicelibrary.repository.AuthorRepository;
import com.library.servicelibrary.service.AuthorService;
import com.library.servicelibrary.service.base.impl.BaseCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends BaseCRUDServiceImpl<Author, Long> implements AuthorService {
    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        super(repository);
    }
}
