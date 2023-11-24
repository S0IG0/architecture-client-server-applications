package com.library.servicelibrary.entity.models;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseEntity<Long> {
    String firstName;
    String lastName;
    @Column(unique = true)
    String pseudonym;
    @ManyToMany(mappedBy = "authors")
    List<Book> books;
}
