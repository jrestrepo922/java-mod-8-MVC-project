package com.flatIron.project8.service;

import com.flatIron.project8.dto.AuthorDTO;
import com.flatIron.project8.dto.CreateAuthorDTO;
import com.flatIron.project8.model.Author;
import com.flatIron.project8.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    public AuthorDTO createAuthor(CreateAuthorDTO createAuthorDTO){
        Author author = mapper.map(createAuthorDTO, Author.class);
        author = authorRepository.save(author);
        return mapper.map(author, AuthorDTO.class);
    }
}
