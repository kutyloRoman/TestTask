package com.kutylo.testtask.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    @Size(max = 15)
    private String username;
    @NotBlank
    @Size(max = 255)
    private String password;
}
