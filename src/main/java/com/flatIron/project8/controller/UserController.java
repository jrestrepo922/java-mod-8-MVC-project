package com.flatIron.project8.controller;

import com.flatIron.project8.dto.*;
import com.flatIron.project8.model.ReadingList;
import com.flatIron.project8.model.User;
import com.flatIron.project8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        UserDTO userDTO = userService.createUser(createUserDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/{id}/reading_lists")
    public ResponseEntity<ReadingListDTO> createReadingListWithRelationToUser(@Valid @PathVariable(value = "id") Integer userId, @RequestBody CreateReadingListDTO createReadingListDTO){
        ReadingListDTO readingListDTO = userService.createReadingListWithRelationToUser(userId, createReadingListDTO);
        return ResponseEntity.ok(readingListDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id){
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/reading_lists")
    public List<ReadingListsDTO> getAllReadingListFromUser(@PathVariable(value = "id") Integer userId){
        return userService.getAllReadingListFromUser(userId);
    }

    @GetMapping("/{id}/reading_lists/{list_id}")
    public ReadingListDTO getReadingListFromUser(@PathVariable(value = "id") Integer userId, @PathVariable(value = "list_id") Integer listId){
        return userService.getReadingListFromUser(userId, listId);
    }

}
