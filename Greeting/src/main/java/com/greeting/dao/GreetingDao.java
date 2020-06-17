package com.greeting.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greeting.model.Users;

@Repository
public interface GreetingDao extends CrudRepository<Users, Long> {

    Users findByfirstName(String username);
}
