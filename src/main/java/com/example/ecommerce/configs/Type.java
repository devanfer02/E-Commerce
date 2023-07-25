package com.example.ecommerce.configs;

public enum Type {
    BUYER,
    SELLER;

    Type() {

    }

    public String toString() {
        switch (this) {
            case BUYER ->  {
                return "buyer";
            }
            case SELLER -> {
                return "seller";
            }
        }
        return "unknown";
    }
}
