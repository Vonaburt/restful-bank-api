package ru.home.restfulbankapi.models;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserInfo implements Serializable {
    private String firstName;
    private String lastName;
    private Short age;
}