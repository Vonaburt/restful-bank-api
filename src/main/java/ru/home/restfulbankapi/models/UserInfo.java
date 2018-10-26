package ru.home.restfulbankapi.models;

import lombok.Data;


@Data
public class UserInfo {
    private String firstName;
    private String lastName;
    private short age;
}