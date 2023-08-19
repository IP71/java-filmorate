package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
Класс, описывающий сущность "Mpa" (возрастной рейтинг фильма)
*/

@Data
@AllArgsConstructor
public class Mpa {

    private int id;
    private String name;
}
