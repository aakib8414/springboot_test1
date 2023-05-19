package com.test.test2.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseDto {
    private DepartmentDto department;
    private UserDto user;
}