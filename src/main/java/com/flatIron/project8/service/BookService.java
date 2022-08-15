package com.flatIron.project8.service;

import com.flatIron.project8.dto.*;
import com.flatIron.project8.model.Author;
import com.flatIron.project8.model.Book;
import com.flatIron.project8.model.Genre;
import com.flatIron.project8.repository.AuthorRepository;
import com.flatIron.project8.repository.BookRepository;
import com.flatIron.project8.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

//    {
//        "title": "not  a good book",
//            "pages": 1,
//            "published": "2015-04-23T18:25:43.511Z",
//            "author": {
//                "name": "Juan valdez"
//    },
//        "genre": [
//        {"name":  "action"},
//        {"name": "adventure"}
//  ]
//    }
    public BookDTO createBook(CreateBookDTO createBookDTO){
        // makes a book using the data from the createBookDTO
        Book book = mapper.map(createBookDTO, Book.class);

        // get the createAuthorDTO and make an author and add the book to books
        CreateAuthorDTO createAuthorDTO = createBookDTO.getAuthor();
        Author author = mapper.map(createAuthorDTO, Author.class);
        // bidirectional mapping
        author.addBook(book);
        book.setAuthor(author);
        authorRepository.save(author);

        // get the List<CreateGenreDTO> and see if they exist in the database already
        // associate them if they exist or not
        List<CreateGenreDTO> genreDTOs = createBookDTO.getGenresNames();
        // find the genre in the database using genreDTOs.getName()
        List<Genre> genres = new ArrayList<Genre>();
        for(int i = 0; i < genreDTOs.size(); i++){
            Optional<Genre> optinalGenre = genreRepository.findByName(genreDTOs.get(i).getName());
            if(optinalGenre.isPresent()){
                // bidirectional mapping of book and genre
                book.addGenre(optinalGenre.get());
                optinalGenre.get().addBook(book);
                genres.add(optinalGenre.get());
            } else {
                // create a new genre
                Genre newGenre = new Genre(genreDTOs.get(i).getName());
                newGenre = genreRepository.save(newGenre);
                //bidirectional mapping
                book.addGenre(newGenre);
                newGenre.addBook(book);
                genres.add(newGenre);
            }
        }

        book = bookRepository.save(book);
        BookDTO bookDTO =  mapper.map(book, BookDTO.class);

        //create  AuthorDTO and pass it to bookDTO
        AuthorDTO authorDTO = mapper.map(author, AuthorDTO.class);
        bookDTO.setAuthor(authorDTO);
        //create List<GenreDTO> and pass it to bookDTO
        List<GenresDTO> genreDTOList = genres.stream()
                .map(genre -> mapper.map(genre, GenresDTO.class )).collect(Collectors.toList());
        bookDTO.setGenres(genreDTOList);
        // return BookDTO
        return bookDTO;
    }

    public BookDTO updateBook(Integer id, UpdateBookDTO updateBookDTO){
        // find the book
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        // update the book
        if(updateBookDTO.getTitle() != null){
            book.setTitle(updateBookDTO.getTitle());
        }

        if(updateBookDTO.getPages() != null){
            book.setPages(updateBookDTO.getPages());
        }

        if(updateBookDTO.getPublished() != null){
            book.setPublished(updateBookDTO.getPublished());
        }
        // save the new book
        book = bookRepository.save(book);
        // return bookDTO
        return mapper.map(book, BookDTO.class);
    }

    public void deleteBook(Integer id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.deleteById(id);
    }

    public List<BooksDTO> getBooks(){
        List<Book> books = bookRepository.findAll();
        List<BooksDTO> booksDTO = books.stream()
                .map(book -> mapper.map(book, BooksDTO.class)).collect(Collectors.toList());
        return booksDTO;
    }

    public BookDTO getBook(Integer id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return mapper.map(book, BookDTO.class);
    }

}
