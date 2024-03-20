package com.chanheng.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParamError {
    private String name;
    private String error;
}

