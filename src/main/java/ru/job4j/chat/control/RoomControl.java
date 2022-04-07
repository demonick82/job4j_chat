package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.serviсe.RoomService;

import java.util.Set;

@RestController
@RequestMapping("/room")
public class RoomControl {

    private final RoomService service;

    public RoomControl(RoomService roomService) {
        this.service = roomService;
    }

    @GetMapping("/")
    public Set<Room> findAllRooms() {
        return service.findAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return service.create(room);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        return service.update(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }
}
