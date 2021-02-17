package io.anymobi.sender.core.exception;

import org.springframework.aop.AopInvocationException;

public class CustomMethodInvokeException extends AopInvocationException {

    public CustomMethodInvokeException(Throwable e) {
        super(e.getMessage());
    }
}
