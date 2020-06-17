package com.greeting.controller;

import com.greeting.model.ApiResponse;
import com.greeting.model.Users;
import com.greeting.model.GreetingDto;
import com.greeting.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class GreetingController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<Users> saveUser(@RequestBody GreetingDto user){
        return new ApiResponse<>(HttpStatus.OK.value(), "greeting saved successfully.",userService.save(user));
    }

    @GetMapping
    public ApiResponse<List<Users>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "greeting list fetched successfully.",userService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Users> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "greeting fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<GreetingDto> update(@RequestBody GreetingDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "greeting updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "greeting deleted successfully.", null);
    }



}
