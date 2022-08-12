package com.flatIron.project8.service;


import com.flatIron.project8.dto.BooksDTO;
import com.flatIron.project8.dto.CreateGenreDTO;
import com.flatIron.project8.dto.GenreDTO;
import com.flatIron.project8.model.Book;
import com.flatIron.project8.model.Genre;
import com.flatIron.project8.repository.BookRepository;
import com.flatIron.project8.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public GenreDTO createGenre(CreateGenreDTO createGenreDTO){
        Genre genre = mapper.map(createGenreDTO, Genre.class);
        genre = genreRepository.save(genre);
        GenreDTO genreDTO =  mapper.map(genre, GenreDTO.class);
        return genreDTO;
    }

    public List<BooksDTO> getBooksFromGenre(Integer genreId){
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        List<Book> books = genre.getBooks();
        List<BooksDTO> booksDTO = books.stream()
                .map(book -> mapper.map(book, BooksDTO.class)).collect(Collectors.toList());
        return booksDTO;
    }


}


