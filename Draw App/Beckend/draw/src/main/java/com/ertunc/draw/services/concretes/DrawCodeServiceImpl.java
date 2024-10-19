package com.ertunc.draw.services.concretes;

import com.ertunc.draw.entities.Code;
import com.ertunc.draw.entities.DrawCode;
import com.ertunc.draw.entities.User;
import com.ertunc.draw.repositories.DrawCodeRepository;
import com.ertunc.draw.services.abstracts.AuthService;
import com.ertunc.draw.services.abstracts.CodeService;
import com.ertunc.draw.services.abstracts.DrawCodeService;
import com.ertunc.draw.services.dtos.requests.AddDrawCodeRequest;
import com.ertunc.draw.services.dtos.responses.AddDrawCodeResponse;
import com.ertunc.draw.services.dtos.responses.CodeByUserIdResponse;
import com.ertunc.draw.services.dtos.responses.ListUserByDrawCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrawCodeServiceImpl implements DrawCodeService {
    private final DrawCodeRepository drawCodeRepository;
    private final AuthService authService;
    private final CodeService codeService;
    @Override
    public AddDrawCodeResponse add(AddDrawCodeRequest addDrawCodeRequest) {
        Optional<User> optionalUser=authService.findById(addDrawCodeRequest.getUserId());
        Optional<DrawCode> optionalDrawCode=drawCodeRepository.findByDrawCode(addDrawCodeRequest.getDrawCode());
        Optional<Code> optionalCode=codeService.findByCode(addDrawCodeRequest.getDrawCode());
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User Not Found!");
        }
        if(optionalDrawCode.isPresent()){
            throw new RuntimeException("This Code Has Already Used");
        }
        if(optionalCode.isEmpty()){
            throw new RuntimeException("You Entered the Wrong Code");
        }
        DrawCode drawCode=new DrawCode();
        drawCode.setUser(optionalUser.get());
        drawCode.setDrawCode(addDrawCodeRequest.getDrawCode());
        DrawCode savedDrawCode=drawCodeRepository.save(drawCode);
        AddDrawCodeResponse response=new AddDrawCodeResponse();
        response.setId(drawCode.getId());
        response.setUserId(drawCode.getUser().getId());
        response.setUserEmail(drawCode.getUser().getEmail());
        response.setDrawCode(drawCode.getDrawCode());
        response.setMessage("Added Successful");
        response.setSuccess(true);
        return response;
    }

    @Override
    public List<CodeByUserIdResponse> getByUserId(int userId) {
        List<DrawCode> drawCodes=drawCodeRepository.findByUserId(userId);
        if(drawCodes.isEmpty()){
            throw new RuntimeException("User Not Found");
        }
        return drawCodes
                .stream()
                .map(drawCode -> new CodeByUserIdResponse(
                    drawCode.getUser().getName(),
                    drawCode.getUser().getSurname(),
                    drawCode.getDrawCode()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ListUserByDrawCode> getUserInfoByDrawCode(String drawCode) {
        Optional<DrawCode> optionalDrawCode=drawCodeRepository.findUserByDrawCode(drawCode);
        if (optionalDrawCode.isEmpty()){
            throw new RuntimeException("You Entered the Wrong Code");
        }
        return optionalDrawCode
                .stream()
                .map(code -> new ListUserByDrawCode(
                        code.getDrawCode(),
                        code.getUser().getId(),
                        code.getUser().getName(),
                        code.getUser().getSurname(),
                        code.getUser().getEmail(),
                        code.getUser().getBirthday()
                ))
                .collect(Collectors.toList());
    }
}
