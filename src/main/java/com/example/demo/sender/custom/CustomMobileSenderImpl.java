package com.example.demo.sender.custom;

import com.example.demo.core.config.annotation.Sender;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Sender
public class CustomMobileSenderImpl{

    int sendCount(){
        return 1000;
    };
}
