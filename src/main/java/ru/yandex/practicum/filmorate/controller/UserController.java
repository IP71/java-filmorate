package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Класс-контроллер для работы с пользователями
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();
    private int id = 0;

    // Метод, обрабатывающий POST запрос (создание пользователя)
    @PostMapping
    public User create(@Valid @RequestBody User user) {
        Validator.validateUser(user);
        user.setId(++id);
        users.put(user.getId(), user);
        log.info("Пользователь {} был создан", user.getEmail());
        return user;
    }

    // Метод, обрабатывающий PUT запрос (обновление данных пользователя)
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        Validator.validateUser(user);
        if (!users.containsKey(user.getId())) {
            throw new ValidationException("Пользователь с таким id не найден");
        }
        users.put(user.getId(), user);
        log.info("Пользователь {} был обновлен", user.getEmail());
        return user;
    }

    // Метод, обрабатывающий GET запрос (получение данных всех пользователей)
    @GetMapping
    public List<User> get() {
        return List.copyOf(users.values());
    }
}
