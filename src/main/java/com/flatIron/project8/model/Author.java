package com.flatIron.project8.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Author")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String name;

    //Relationships

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<Book>();

    public void addBook(Book book ){
        this.books.add(book);
    }

}
