package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListAddressResponse {
    private int id;
    private String description;
    private String districtName;
    private String cityName;
}
