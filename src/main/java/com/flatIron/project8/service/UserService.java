package com.flatIron.project8.service;

import com.flatIron.project8.dto.*;
import com.flatIron.project8.model.ReadingList;
import com.flatIron.project8.model.User;
import com.flatIron.project8.repository.ReadingListRepository;
import com.flatIron.project8.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReadingListRepository readingListRepository;

    @Autowired
    private ModelMapper mapper;


    public UserDTO  createUser(CreateUserDTO createUserDTO){
        // copy from and create here
        User user = mapper.map(createUserDTO, User.class);
        // users have many ReadingList but this is already taken into account in the model
        // users can have no ReadingList
        user = userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        // @OnDelete(action = OnDeleteAction.CASCADE) should take care of the readingList but need to ask this
        userRepository.deleteById(id);
    }

    public List<ReadingListsDTO> getAllReadingListFromUser(Integer userId){
        // get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        //get users ReadingList
        List<ReadingList> readingLists = user.getReadingLists();
        List<ReadingListsDTO> readingListsDTO = readingLists.stream()
                .map(readingList -> mapper.map(readingList, ReadingListsDTO.class)).collect(Collectors.toList());
        return readingListsDTO;
    }

    public ReadingListDTO createReadingListWithRelationToUser(Integer userId, CreateReadingListDTO createReadingListDTO) {
        // get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        // will need to create a single ReadingList and associate to user
        ReadingList readingList = mapper.map(createReadingListDTO, ReadingList.class);
        readingList.setLibraryUser(user);
        user.addReadingList(readingList);
        userRepository.save(user);
        readingListRepository.save(readingList);
        return mapper.map(readingList, ReadingListDTO.class);
    }

    public ReadingListDTO getReadingListFromUser(Integer userId, Integer listId){
        // get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // get readingList  that belongs to this user. Could do this with a custom query or add logic here
        List<ReadingList> readingLists = user.getReadingLists();

        readingLists = readingLists.stream().filter(readingListStream -> readingListStream.getId() == listId ).collect(Collectors.toList());
        if(readingLists.size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not contain that reading list");
        }

        return mapper.map(readingLists.get(0), ReadingListDTO.class);
    }

}
