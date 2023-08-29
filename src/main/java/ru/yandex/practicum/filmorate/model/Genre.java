package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
Класс, описывающий сущность "Жанр фильма"
*/

@Data
@AllArgsConstructor
public class Genre {

    private int id;
    private String name;
}
