package com.library.servicelibrary.service;

import com.library.servicelibrary.entity.models.Library;
import com.library.servicelibrary.service.base.BaseCRUDService;

public interface LibraryService extends BaseCRUDService<Library, Long> {
    String addBook(Long libraryId, Long bookId);
}
