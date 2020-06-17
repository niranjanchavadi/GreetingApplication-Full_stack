package com.greeting.service;

import java.util.List;

import com.greeting.model.Users;
import com.greeting.model.GreetingDto;

public interface UserService {

    Users save(GreetingDto user);
    List<Users> findAll();
    void delete(int id);

    Users findOne(String username);

    Users findById(int id);

    GreetingDto update(GreetingDto userDto);
}
