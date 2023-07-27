package com.example.ecommerce.address;

import com.example.ecommerce.configs.Response;
import com.example.ecommerce.configs.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) { this.addressService = addressService; }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getAddressById(@PathVariable Integer userId) {
        try {
            Address address = addressService.getAddress(userId);

            return Response.generateResponse(HttpStatus.OK, "successfully fetch data", address);
        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "data not found", null);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> postAddress(@RequestBody Address address, @PathVariable Integer userId) {
        try {
            Status request = addressService.verify(address, userId);

            if (request == Status.NOT_FOUND) {
                return Response.generateResponse(HttpStatus.NOT_FOUND, "user not found", null);
            }

            if (request == Status.VALUES_STILL_NULL) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "request values still null", null);
            }

            if (request == Status.USER_EXIST) {
                return Response.generateResponse(HttpStatus.CONFLICT, "user address already exist", null);
            }

            int rowsAffected = addressService.addAddress(address);

            if (rowsAffected == 0){
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to add data", null);
            }

            return Response.generateResponse(HttpStatus.CREATED, "successfully add data", address);

        } catch (Exception exception) {
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> patchAddress(@PathVariable Integer userId, @RequestBody Address address) {
        try {
            int rowsAffected = addressService.updateAddress(userId, address);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to update data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully update data", null);

        } catch (EmptyResultDataAccessException exception) {
            return Response.generateResponse(HttpStatus.NOT_FOUND, "user not found", null);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Integer userId) {
        try {
            int rowsAffected = addressService.removeAddress(userId);

            if (rowsAffected < 1) {
                return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to delete data", null);
            }

            return Response.generateResponse(HttpStatus.OK, "successfully update data", null);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return Response.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error", null);
        }
    }
}
