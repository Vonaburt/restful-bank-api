package ru.home.restfulbankapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserInfo implements Serializable {
    private String firstName;
    private String lastName;
    private Short age;
}