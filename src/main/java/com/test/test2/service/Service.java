package com.test.test2.service;

import com.test.test2.dto.ResponseDto;
import com.test.test2.entity.User;

public interface Service {
    User saveUser(User user);

    ResponseDto getUser(Long userId);
    void deleteAll();
}