package com.flatIron.project8.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String title;

    @Min(value = 1)
    private Integer pages;

    private LocalDate published;

    // relationships

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ReadingList> readingLists = new ArrayList<ReadingList>();

    // NO NEED TO MESS WITH THE JOIN TABLE JUST USED PLAIN OLD JAVA TO DO RELATIONSHIP
    @ManyToMany(mappedBy = "books")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Genre> genres = new ArrayList<Genre>();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;

    public void addGenre(Genre genre){
        this.genres.add(genre);
    }
}
