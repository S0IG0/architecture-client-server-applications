package com.library.servicelibrary.entity.models;

import com.library.servicelibrary.entity.models.base.BaseEntity;
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
public class Book extends BaseEntity<Long> {
    String name;
    String description;
    @ManyToMany
    List<Author> authors;
}
