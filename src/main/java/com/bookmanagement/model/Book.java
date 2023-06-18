package com.bookmanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @Indexed(unique = true)
    @NotBlank
    private Long id;
    @NotBlank
    private String bookName;
    @NotEmpty
    private String author;
    private String publicationDate;
    private Date createDate= new Date();
    @Size(min = 1, max = 100)
    private Integer printing;

}
