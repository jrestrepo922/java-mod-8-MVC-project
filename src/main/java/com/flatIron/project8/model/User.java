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
@Table(name = "LibraryUser")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "libraryUser")
    List<ReadingList> ReadingLists = new ArrayList<ReadingList>();

    public void addReadingList(ReadingList readingList){
        this.ReadingLists.add(readingList);
    }
}
