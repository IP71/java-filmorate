package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        validate(user);
        user.setId(++id);
        users.put(user.getId(), user);
        log.info("Пользователь {} был создан", user.getEmail());
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        validate(user);
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с таким id не найден");
        }
        users.put(user.getId(), user);
        log.info("Пользователь {} был обновлен", user.getEmail());
        return user;
    }

    @GetMapping
    public List<User> get() {
        return List.copyOf(users.values());
    }

    public void validate(User user) {
        if (!user.getEmail().contains("@")) {
            log.error("Email пользователя пуст или не содержит @");
            throw new ValidationException("Email должен содержать @ и не быть пустым");
        }
        if (user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.error("Логин пользователя пуст или содержит пробелы");
            throw new ValidationException("Логин не должен быть пустым или содержать пробелы");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
            log.info("Имя пользователя пустое, будет использован логин");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Дата рождения пользователя в будущем");
            throw new ValidationException("Дата рождения не может быть в будущем");
        }
    }
}
