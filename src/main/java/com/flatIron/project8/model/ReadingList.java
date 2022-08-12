package com.flatIron.project8.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReadingList")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadingList {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String name;

    //relationships

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User libraryUser;

    @ManyToMany(mappedBy = "readingLists")
    private List<Book> books = new ArrayList<Book>();


}
