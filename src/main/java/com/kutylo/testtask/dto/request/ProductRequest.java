package com.kutylo.testtask.dto.request;

import com.kutylo.testtask.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    @Size(max = 32)
    private String name;
    @Size(max = 255)
    private String description;
    @NotNull
    @Positive
    private double price;
    @NotNull
    private CategoryResponse category;
}
