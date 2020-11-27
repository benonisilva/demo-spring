package com.benoni.spring.interview.demo.services;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "cliente not found")
public class NotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5313902611499473789L;

}
