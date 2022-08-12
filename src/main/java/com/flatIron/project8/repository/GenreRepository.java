package com.flatIron.project8.repository;

import com.flatIron.project8.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Optional<Genre> findByName(String name);
}
