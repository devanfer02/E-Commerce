package com.example.ecommerce.configs;

public enum Status {
    OK(0),
    EMAIL_EXIST(-1),
    VALUES_STILL_NULL(-2),
    NOT_FOUND(-3),
    DIFF_TYPE(-4);
    
    private int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
