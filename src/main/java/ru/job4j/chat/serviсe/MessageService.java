package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public List<Message> findAllMessages() {
        return repository.findAll();
    }

    public ResponseEntity<Message> findById(int id) {
        Optional<Message> message = repository.findById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Message> create(Message message) {
        return new ResponseEntity<>(
                this.repository.save(message),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> update(Message message) {
        repository.save(message);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> delete(int id) {
        Message message = new Message();
        message.setId(id);
        repository.delete(message);
        return ResponseEntity.ok().build();
    }
}
