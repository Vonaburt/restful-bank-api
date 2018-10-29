package ru.home.restfulbankapi.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public enum Currency implements Serializable {

    RUR(1),
    EUR(2),
    USD(3);

    @Getter
    @Setter
    private Integer code;

    Currency(Integer code) {
        this.code = code;
    }
}
