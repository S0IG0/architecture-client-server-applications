package com.library.servicelibrary.entity.models;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Library extends BaseEntity<Long> {
    String name;
    @Column(unique = true)
    String address;
    @ManyToMany(mappedBy = "libraries")
    List<Book> books;
}
