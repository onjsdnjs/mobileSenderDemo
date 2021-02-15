package com.example.demo.core.sender;

import org.springframework.stereotype.Indexed;

@Indexed
public interface MobileSender<REQ, RES> extends CrudSender<REQ, RES> {

    void send(REQ req);

    RES receive(RES R);
}
