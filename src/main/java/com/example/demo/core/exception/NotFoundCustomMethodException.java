package com.example.demo.core.exception;

import org.springframework.aop.AopInvocationException;

public class NotFoundCustomMethodException extends AopInvocationException {

    public NotFoundCustomMethodException(Throwable e) {
        super(e.getMessage());
    }
}
