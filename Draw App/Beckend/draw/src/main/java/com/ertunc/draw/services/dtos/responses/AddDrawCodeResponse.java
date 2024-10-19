package com.ertunc.draw.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddDrawCodeResponse {
    private int id;
    private int userId;
    private String userEmail;
    private String drawCode;
    private String message;
    private boolean success;
}
