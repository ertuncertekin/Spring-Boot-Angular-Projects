package com.example.recipe.services.abstracts;

import com.example.recipe.services.dtos.requests.AddAddressRequest;
import com.example.recipe.services.dtos.requests.UpdateAddressRequest;
import com.example.recipe.services.dtos.responses.AddAddressResponse;
import com.example.recipe.services.dtos.responses.ListAddressResponse;
import com.example.recipe.services.dtos.responses.UpdateAddressResponse;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface AddressService {

    AddAddressResponse add(AddAddressRequest addAddressRequest);

    UpdateAddressResponse update(UpdateAddressRequest updateAddressRequest);

    void delete(int id);

    List<ListAddressResponse> getByUserId(int userId);
}
