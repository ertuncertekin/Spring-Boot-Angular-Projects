package com.example.recipe.services.mappers;

import com.example.recipe.entities.Address;
import com.example.recipe.services.dtos.requests.AddAddressRequest;
import com.example.recipe.services.dtos.requests.UpdateAddressRequest;
import com.example.recipe.services.dtos.responses.AddAddressResponse;
import com.example.recipe.services.dtos.responses.UpdateAddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "userId",target = "user.id")
    @Mapping(source = "districtId",target = "district.id")
    Address addressFromAddRequest(AddAddressRequest addAddressRequest);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "districtId",source = "district.id")
    AddAddressResponse toResponse(Address address);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "districtId",source = "district.id")
    UpdateAddressResponse toUpdateResponse(Address address);

    void updateAddressFromRequest(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);
}
