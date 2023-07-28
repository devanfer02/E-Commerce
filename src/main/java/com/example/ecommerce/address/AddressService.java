package com.example.ecommerce.address;

import com.example.ecommerce.configs.Database;
import com.example.ecommerce.configs.Status;
import com.example.ecommerce.configs.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final Database db;

    @Autowired
    public AddressService(Database db) { this.db = db; }

    public Address getAddress(Integer id) {
        String query = "SELECT * FROM addresses WHERE user_id = ?";

        return db.fetchByOne(query, new Object[]{id}, new AddressRowMapper());
    }

    public int addAddress(Address address){
        String query = "INSERT INTO addresses (user_id, street, city, province, postcode, country) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{
                address.getUser(),
                address.getStreet(),
                address.getCity(),
                address.getProvince(),
                address.getPostcode(),
                address.getCountry()
        };

        return db.insert(query, params);
    }

    public int updateAddress(Integer userId, Address address) {
        String query = "UPDATE addresses SET street = ?, city = ?, province = ?, postcode = ?, country = ? WHERE user_id = ?";
        Address addressInDb = db.fetchByColumnInTable("addresses", "user_id", userId, new AddressRowMapper());

        Utils.updateObjectValues(addressInDb, address);
        System.out.println(addressInDb);
        Object[] params = new Object[]{
                addressInDb.getStreet(),
                addressInDb.getCity(),
                addressInDb.getProvince(),
                addressInDb.getPostcode(),
                addressInDb.getCountry(),
                userId
        };

        return  db.insert(query, params);
    }

    public int removeAddress(Integer userId) {
        return db.remove("addresses", "user_id", new Object[]{userId});
    }

    public Status verify(Address address, Integer userId) {
        boolean userNotExist = !db.findOneInTable("users", new Object[]{userId});

        if (userNotExist) {
            return Status.NOT_FOUND;
        }

        boolean addressAlreadyExist = db.findOneInTable("addresses", "user_id", new Object[]{userId});

        if (addressAlreadyExist) {
            return Status.USER_EXIST;
        }

        address.setUser(userId);
        boolean stillNull = Utils.nullChecker(address);

        if (stillNull) {
            return Status.VALUES_STILL_NULL;
        }

        return Status.OK;
    }
}
