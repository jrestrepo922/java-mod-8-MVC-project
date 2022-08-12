package com.flatIron.project8.dto;


import com.flatIron.project8.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingListDTO {

    private int id;

    @NotBlank
    private String name;

    private List<BooksDTO> books = new ArrayList<BooksDTO>();
}
