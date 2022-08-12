package com.flatIron.project8.repository;

import com.flatIron.project8.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer>    {

}
