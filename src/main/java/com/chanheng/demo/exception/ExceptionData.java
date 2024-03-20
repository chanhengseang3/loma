package com.chanheng.demo.exception;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ExceptionData {
    private int code;
    private String message;
    private List<ParamError> errors;
}
