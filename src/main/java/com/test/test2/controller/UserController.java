package com.test.test2.controller;

import com.test.test2.dto.ResponseDto;
import com.test.test2.entity.User;
import com.test.test2.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private Service service;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User savedUser = service.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId){
        ResponseDto responseDto = service.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted ");
    }
}