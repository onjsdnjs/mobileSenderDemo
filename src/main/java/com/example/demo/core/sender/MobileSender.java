package com.example.demo.core.sender;

import com.example.demo.core.config.annotation.NoMobileSenderBean;

@NoMobileSenderBean
public interface MobileSender<REQ, RES> extends CrudSender<REQ, RES> {

    void send(REQ req);

    RES receive(RES R);
}
