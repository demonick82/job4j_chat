package ru.job4j.chat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Authority;

import java.util.List;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    List<Authority> findAll();
}
