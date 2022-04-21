package com.example.pruebacovinoc.exceptions;

import com.example.pruebacovinoc.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException extends CovinocException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String code, String message) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
    }

    public InternalServerErrorException(String code, String message, ErrorDto data) {
        super(code,HttpStatus.INTERNAL_SERVER_ERROR.value(),message, Arrays.asList(data));
    }

}
