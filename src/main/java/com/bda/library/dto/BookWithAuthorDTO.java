package com.bda.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookWithAuthorDTO {
    private Long bookId;
    private String title;
    private String isbn;
    private int publishedYear;
    private String genre;
    private Long authorId;
    private String authorName;
    private String authorNationality;
}
