package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

/*
Класс-контроллер для работы с пользователями
Метод create(User user) добавляет нового пользователя в базу (POST /users)
Метод update(User user) обновляет информацию о пользователе (PUT /users)
Метод get() возвращает информацию о всех пользователях (GET /users)
Метод getUserById(long id) возвращает информацию о пользователе по id (GET /users/{id})
Метод addFriend(long id, long friendId) добавляет пользователей в список друзей (PUT users/{id}/friends/{friendId})
Метод removeFriend(long id, long friendId) удаляет пользователей из списка друзей (DELETE users/{id}/friends/{friendId})
Метод getFriends(long id) возвращает информацию о друзьях пользователя (GET users/{id}/friends)
Метод getCommonFriends(long id, long otherId) возвращает информацию об общих друзьях (GET users/{id}/friends/common/{otherId})
 */

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        Validator.validateUser(user);
        return service.create(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        Validator.validateUser(user);
        return service.update(user);
    }

    @GetMapping
    public List<User> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return service.getUserById(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable long id, @PathVariable long friendId) {
        service.addToFriendsList(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable long id, @PathVariable long friendId) {
        service.deleteFromFriendsList(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@PathVariable long id) {
        return service.getFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable long id, @PathVariable long otherId) {
        return service.getCommonFriends(id, otherId);
    }
}
