package com.test.test2.entity;

import com.test.test2.validate.TextValid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginData {

    @NotBlank(message = "User Name cant be blank")
    @NotNull(message = "User can not be null")
    @Size(min = 3,max = 15 ,message = "Length must be 3-15 range character")
    @TextValid//custom validator
    private String userName;
    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email cant be blank")
    private String email;
    @AssertTrue(message = "")
    private boolean terms;
}
