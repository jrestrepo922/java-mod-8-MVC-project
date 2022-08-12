package com.flatIron.project8.controller;

import com.flatIron.project8.dto.BookDTO;
import com.flatIron.project8.dto.BooksDTO;
import com.flatIron.project8.dto.CreateBookDTO;
import com.flatIron.project8.dto.UpdateBookDTO;
import com.flatIron.project8.repository.BookRepository;
import com.flatIron.project8.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDTO createBookDTO){
        BookDTO bookDTO = bookService.createBook(createBookDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@Valid @PathVariable(value = "id") Integer id , @Valid @RequestBody UpdateBookDTO updateBookDTO){
        BookDTO bookDTO = bookService.updateBook(id, updateBookDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable(value = "id") Integer id) {
        bookService.deleteBook(id);
    }

    @GetMapping()
    public List<BooksDTO> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable(value = "id") Integer id){
        return bookService.getBook(id);
    }



}
