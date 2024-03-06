package com.example.userservice.modelDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotNull(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+91-[1-9]\\d{9}$",message = "Please provide valid phone number")
    private String phNo;
}
