package com.ertunc.draw.services.abstracts;

import com.ertunc.draw.services.dtos.requests.AddDrawCodeRequest;
import com.ertunc.draw.services.dtos.responses.AddDrawCodeResponse;
import com.ertunc.draw.services.dtos.responses.CodeByUserIdResponse;
import com.ertunc.draw.services.dtos.responses.ListUserByDrawCode;

import java.util.List;
import java.util.Optional;

public interface DrawCodeService {
    AddDrawCodeResponse add(AddDrawCodeRequest addDrawCodeRequest);
    List<CodeByUserIdResponse> getByUserId(int userId);
    List<ListUserByDrawCode> getUserInfoByDrawCode(String drawCode);
}
