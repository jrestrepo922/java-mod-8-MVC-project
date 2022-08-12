package com.flatIron.project8.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReadingListDTO {
    @NotBlank
    private String name;
}
