package ru.job4j.chat.model;

import ru.job4j.chat.exceptionHandling.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Min(value = 1, message = "id должно быть больше единицы",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    private int id;

    @NotBlank(message = "Поле role не должно быть пустым")
    private String authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority = (Authority) o;
        return id == authority.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Authority.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("authority='" + authority + "'")
                .toString();
    }
}