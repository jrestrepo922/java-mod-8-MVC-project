package com.flatIron.project8.dto;

import com.flatIron.project8.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private int id;

    @NotBlank
    private String title;

    @Min(value = 1)
    private Integer pages;

    private LocalDate published;

    private AuthorDTO author;

    private List<GenresDTO> genres;

    public  void addGenreDTO(GenresDTO genresDTO){
        this.genres.add(genresDTO);
    }

}
