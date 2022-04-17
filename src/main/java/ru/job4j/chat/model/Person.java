package ru.job4j.chat.model;

import ru.job4j.chat.exceptionHandling.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Min(value = 1, message = "id должно быть больше единицы",
            groups = {Operation.OnUpdate.class, Operation.OnDelete.class})
    private int id;

    @Column(name = "nickname")
    @NotBlank(message = "Поле не должно быть пустым")
    private String nickName;

    @NotBlank(message = "Поле не должно быть пустым")
    private String login;

    @Min(value = 6, message = "Пароль должен быть больше 6 символов")
    private String password;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;


    public static Person of(String nickName, String login, String password) {
        Person person = new Person();
        person.nickName = nickName;
        person.login = login;
        person.password = password;
        return person;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
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
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nickName='" + nickName + "'")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("authority=" + authority)
                .toString();
    }
}
