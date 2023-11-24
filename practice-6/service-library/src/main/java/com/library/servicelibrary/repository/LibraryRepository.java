package com.library.servicelibrary.repository;

import com.library.servicelibrary.entity.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
