package com.flatIron.project8.repository;

import com.flatIron.project8.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
