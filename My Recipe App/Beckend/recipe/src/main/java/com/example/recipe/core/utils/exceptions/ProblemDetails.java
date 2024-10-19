package com.example.recipe.core.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.PrimitiveIterator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetails {
    private String title;
    private String detail;
    private String type;
    private int status;
}
