package com.flatIron.project8.controller;

import com.flatIron.project8.dto.BooksDTO;
import com.flatIron.project8.dto.CreateGenreDTO;
import com.flatIron.project8.dto.GenreDTO;
import com.flatIron.project8.model.Genre;
import com.flatIron.project8.repository.GenreRepository;
import com.flatIron.project8.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;


    // create a post for genre that has book associations
    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody CreateGenreDTO createGenreDTO){
        GenreDTO genreDTO = genreService.createGenre(createGenreDTO);
        return  ResponseEntity.ok(genreDTO);
    }


    // create a get to get all the books from a specific genre
    @GetMapping("/{id}/books")
    public List<BooksDTO> getBooksFromGenre(@PathVariable(value = "id") Integer id){
        return genreService.getBooksFromGenre(id);
    }
}
