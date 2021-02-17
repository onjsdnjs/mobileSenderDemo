package io.anymobi.sender.core.sender;

import io.anymobi.sender.core.config.annotation.NoMobileSenderBean;

@NoMobileSenderBean
public interface MobileSender<REQ, RES> extends CrudSender<REQ, RES> {

    void send(REQ req);

    RES receive(RES R);
}
