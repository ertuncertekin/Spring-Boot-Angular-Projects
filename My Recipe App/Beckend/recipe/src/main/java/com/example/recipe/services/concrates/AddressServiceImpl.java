package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Address;
import com.example.recipe.entities.District;
import com.example.recipe.entities.User;
import com.example.recipe.repositories.AddressRepository;
import com.example.recipe.services.abstracts.AddressService;
import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.abstracts.DistrictService;
import com.example.recipe.services.dtos.requests.AddAddressRequest;
import com.example.recipe.services.dtos.requests.UpdateAddressRequest;
import com.example.recipe.services.dtos.responses.AddAddressResponse;
import com.example.recipe.services.dtos.responses.ListAddressResponse;
import com.example.recipe.services.dtos.responses.UpdateAddressResponse;
import com.example.recipe.services.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AuthService authService;
    private final DistrictService districtService;
    @Override
    public AddAddressResponse add(AddAddressRequest addAddressRequest) {
        Optional<User> user=authService.findById(addAddressRequest.getUserId());
        Optional<District> district=districtService.findById(addAddressRequest.getDistrictId());

        if(user.isEmpty()){
        throw new BusinessException("User Not Found");
        }
        else if (district.isEmpty()){
            throw new BusinessException("District Not Found");
        }
        else{
            Address address= AddressMapper.INSTANCE.addressFromAddRequest(addAddressRequest);
            Address savedAddress=addressRepository.save(address);
            AddAddressResponse response=AddressMapper.INSTANCE.toResponse(savedAddress);
            response.setSuccess(true);
            response.setMessage("Address successfully added");
            return response;
        }
    }

    @Override
    public UpdateAddressResponse update(UpdateAddressRequest updateAddressRequest) {
        Optional<Address> optionalAddress=addressRepository.findById(updateAddressRequest.getId());
        if(optionalAddress.isEmpty()){
            throw new BusinessException("Address Not Found");
        }
        Optional<District> optionalDistrict=districtService.findById(updateAddressRequest.getDistrictId());
        if(optionalDistrict.isEmpty()){
            throw new BusinessException("District Not Found");
        }
        Address address=optionalAddress.get();
        District district=optionalDistrict.get();
        address.setDistrict(district);
        AddressMapper.INSTANCE.updateAddressFromRequest(updateAddressRequest,address);
        Address savedAddress=addressRepository.save(address);
        UpdateAddressResponse response=AddressMapper.INSTANCE.toUpdateResponse(savedAddress);
        response.setSuccess(true);
        response.setMessage("Address successfully updated");
        return response;
    }

    @Override
    public void delete(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<ListAddressResponse> getByUserId(int userId) {
        List<Address> addresses=addressRepository.findByUserId(userId);
        if (addresses.isEmpty()){
            throw new BusinessException("The user does not have a registered address");
        }
        return addresses.stream().map(this::convertToAddressResponse).collect(Collectors.toList());
    }

    private ListAddressResponse convertToAddressResponse(Address address){
        return new ListAddressResponse(
                address.getId(),
                address.getDescription(),
                address.getDistrict().getName(),
                address.getDistrict().getCity().getName()
        );
    }
}
