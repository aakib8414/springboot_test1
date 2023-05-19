package com.test.test2.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}