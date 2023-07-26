package com.example.ecommerce.user;

import com.example.ecommerce.configs.Type;


public class User {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Type type;

    public User() {
    }

    public User(Integer id, String first_name, String last_name, String email, String phone_number, Type type) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.type = type;
    }

    public User(String first_name, String last_name, String email, String phone_number, Type type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.type = type;
    }

    public User(String first_name, String last_name, String email, String phone_number) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.type = type.equals("buyer") ? Type.BUYER : Type.SELLER;
    }

    public void update(User newData) {
        if(newData.first_name != null) {
            this.first_name = newData.first_name;
        }
        if(newData.last_name != null) {
            this.last_name = newData.last_name;
        }
        if(newData.email != null) {
            this.email = newData.email;
        }
        if(newData.phone_number != null) {
            this.phone_number = newData.phone_number;
        }
        if(newData.type != null) {
            this.type = newData.type;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", type=" + type +
                '}';
    }
}
