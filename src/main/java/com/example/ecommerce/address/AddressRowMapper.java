package com.example.ecommerce.address;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet res, int rowNum) throws SQLException {
        Address address = new Address();

        address.setUser(res.getInt("user_id"));
        address.setStreet(res.getString("street"));
        address.setProvince(res.getString("province"));
        address.setCity(res.getString("city"));
        address.setPostcode(res.getString("postcode"));
        address.setCountry(res.getString("country"));

        return address;
    }
}
