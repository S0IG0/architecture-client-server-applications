package com.library.servicelibrary.entity.models;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity<Long> {
    String name;
    @Column(columnDefinition = "text")
    String description;
    @ManyToMany
    List<Author> authors;
    @ManyToMany
    List<Library> libraries;
}
