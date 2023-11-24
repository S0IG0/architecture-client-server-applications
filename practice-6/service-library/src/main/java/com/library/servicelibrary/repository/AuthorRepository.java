package com.library.servicelibrary.repository;

import com.library.servicelibrary.entity.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
