package com.flatIron.project8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDTO {

    @NotBlank
    private String title;

    @Min(value = 1)
    private Integer pages;

    private LocalDate published;

    private List<CreateGenreDTO> genresNames = new ArrayList<CreateGenreDTO>();

    private CreateAuthorDTO author;
}
