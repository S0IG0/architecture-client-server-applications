package com.library.servicelibrary.entity.dto.response;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse extends BaseEntity<Long> {
    String name;
    String address;
    List<BookResponse> books;
}
