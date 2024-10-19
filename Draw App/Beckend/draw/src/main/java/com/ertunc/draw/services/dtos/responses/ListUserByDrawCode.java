package com.ertunc.draw.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListUserByDrawCode {
    private String drawCode;
    private int userId;
    private String userName;
    private String userSurname;
    private String userEmail;
    private LocalDate birthDay;
}
