package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    List<Room> findAll();
}