package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.AddressService;
import com.example.recipe.services.dtos.requests.AddAddressRequest;
import com.example.recipe.services.dtos.requests.UpdateAddressRequest;
import com.example.recipe.services.dtos.responses.AddAddressResponse;
import com.example.recipe.services.dtos.responses.ListAddressResponse;
import com.example.recipe.services.dtos.responses.UpdateAddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("createaddress")
    @ResponseStatus(HttpStatus.CREATED)
    public AddAddressResponse createAddress(@Valid @RequestBody AddAddressRequest addAddressRequest){
        return  addressService.add(addAddressRequest);
    }

    @PutMapping("updateaddress")
    public UpdateAddressResponse update(@Valid @RequestBody UpdateAddressRequest updateAddressRequest){
        return addressService.update(updateAddressRequest);
    }

    @DeleteMapping("deleteaddress")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@RequestParam int id){
        addressService.delete(id);
    }

    @GetMapping("usersaddresses/{userId}")
    public List<ListAddressResponse> getAddressByUser(@PathVariable int userId){
        return addressService.getByUserId(userId);
    }
}
