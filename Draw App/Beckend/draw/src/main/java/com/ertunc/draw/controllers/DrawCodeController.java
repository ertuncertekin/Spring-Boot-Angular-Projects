package com.ertunc.draw.controllers;

import com.ertunc.draw.services.abstracts.DrawCodeService;
import com.ertunc.draw.services.dtos.requests.AddDrawCodeRequest;
import com.ertunc.draw.services.dtos.responses.AddDrawCodeResponse;
import com.ertunc.draw.services.dtos.responses.CodeByUserIdResponse;
import com.ertunc.draw.services.dtos.responses.ListUserByDrawCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drawcode")
@RequiredArgsConstructor
public class DrawCodeController {
    private final DrawCodeService drawCodeService;

    @PostMapping("join")
    public AddDrawCodeResponse register(@RequestBody @Valid AddDrawCodeRequest request){
        return drawCodeService.add(request);
    }

    @GetMapping("/getbyuser/{userId}")
    public List<CodeByUserIdResponse> getFromUserId(@PathVariable int userId){
        return drawCodeService.getByUserId(userId);
    }

    @GetMapping("/getuserbydrawcode/")
    public List<ListUserByDrawCode> getFromDrawCode(@RequestParam String code){
        return drawCodeService.getUserInfoByDrawCode(code);
    }
}
