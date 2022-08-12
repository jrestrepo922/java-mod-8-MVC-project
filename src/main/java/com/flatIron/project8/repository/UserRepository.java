package com.flatIron.project8.repository;

import com.flatIron.project8.model.ReadingList;
import com.flatIron.project8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
