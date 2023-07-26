package com.example.ecommerce.configs;

public enum Type {
    BUYER,
    SELLER;

    Type() {

    }

    public String toString() {
        try {
            switch (this) {
                case BUYER ->  {
                    return "buyer";
                }
                case SELLER -> {
                    return "seller";
                }

            }
            throw new IllegalArgumentException("Unexpected value");

        } catch ( IllegalArgumentException exception ){
            System.out.println(exception.getMessage());
        }

        return "";
    }
}
