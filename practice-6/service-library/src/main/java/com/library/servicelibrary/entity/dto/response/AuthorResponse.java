package com.library.servicelibrary.entity.dto.response;

import com.library.servicelibrary.entity.models.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse extends BaseEntity<Long> {
    String firstName;
    String lastName;
    String pseudonym;
}
