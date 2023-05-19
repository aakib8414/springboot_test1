package com.test.test2.service;

import com.test.test2.adapter.UserAdapter;
import com.test.test2.dto.DepartmentDto;
import com.test.test2.dto.ResponseDto;
import com.test.test2.dto.UserDto;
import com.test.test2.entity.User;
import com.test.test2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@AllArgsConstructor
@Slf4j
public class UserService implements Service {

    private UserRepository userRepository;

    private UserAdapter adapter;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDto getUser(Long userId) {

        ResponseDto.ResponseDtoBuilder builder = ResponseDto.builder();
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("User not exist for id:" + userId);
        });
        UserDto userDto = mapToUser(user);
        DepartmentDto departmentDto = adapter.getDeptDto(user.getDepartmentId());
        builder.user(userDto)
                .department(departmentDto);
        adapter.getDtoList();
        adapter.saveDeptDto(departmentDto);
        return builder.build();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();

    }

    private UserDto mapToUser(User user) {

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
