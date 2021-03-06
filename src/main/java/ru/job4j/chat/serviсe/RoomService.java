package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {
    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public Set<Room> findAllRooms() {
        return repository.findAll();
    }

    public ResponseEntity<Room> findById(int id) {
        Optional<Room> room = repository.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Room> create(Room room) {
        return new ResponseEntity<>(
                this.repository.save(room),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> update(Room room) {
        repository.save(room);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        Room room = new Room();
        room.setId(id);
        repository.delete(room);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Room> patch(RoomDTO roomDTO) {
        Room room = Room.of(roomDTO.getName());
        room.setId(roomDTO.getId());
        return new ResponseEntity<>(
                this.repository.save(room),
                HttpStatus.CREATED);
    }
}
