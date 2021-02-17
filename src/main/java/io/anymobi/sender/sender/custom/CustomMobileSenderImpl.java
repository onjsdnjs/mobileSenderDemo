package io.anymobi.sender.sender.custom;

import io.anymobi.sender.core.config.annotation.Sender;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Sender
public class CustomMobileSenderImpl{

    int sendCount(){
        return 1000;
    };
}
