package com.example.ecommerce.user;

import com.example.ecommerce.configs.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers(@RequestParam(value= "type", required = false) String type) {
        List<User> data = null;
        if ( type != null ) {
            data = userService.getUsers(type);
        } else {
            data = userService.getUsers();
        }
        return Response.generateResponse(HttpStatus.OK, "successfully fetch data", data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
        try {
            User data = userService.getUserById(id);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", data);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> postUser(@RequestBody User user) {
        try {
            int rowsAffected = userService.addNewUser(user);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to add data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully add data", user);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            int rowsAffected = userService.updateUser(id, user);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to update data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully update data", null);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        try {
            int rowsAffected = userService.removeUser(id);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "failed to delete data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully delete data", null);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }
}
