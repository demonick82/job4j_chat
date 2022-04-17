package ru.job4j.chat.serviсe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.chat.dto.MessageDTO;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository repository;
    private final PersonService personService;

    public MessageService(MessageRepository repository, PersonService personService) {
        this.repository = repository;
        this.personService = personService;
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

    public ResponseEntity<Message> patch(MessageDTO messageDTO) {
        Message message = Message.of(messageDTO.getDescription());
        Person person = personService.findById(messageDTO.getPersonId()).getBody();
        message.setId(messageDTO.getId());
        message.setPerson(person);
        return new ResponseEntity<>(
                this.repository.save(message),
                HttpStatus.CREATED);
    }
}
