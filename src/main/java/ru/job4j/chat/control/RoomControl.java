package ru.job4j.chat.control;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.exceptionHandling.Operation;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.serviсe.RoomService;

import javax.validation.Valid;
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
        ResponseEntity<Room> room = service.findById(id);
        if (room.getStatusCodeValue() == 404) {
            throw new NullPointerException("Room with it id=" + id + " not found in database");
        }
        return room;
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Room> create(@Valid @RequestBody Room room) {
        return service.create(room);
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Room room) {
        return service.update(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Room> patch(@Valid @RequestBody RoomDTO roomDTO) {
        return service.patch(roomDTO);
    }
}
