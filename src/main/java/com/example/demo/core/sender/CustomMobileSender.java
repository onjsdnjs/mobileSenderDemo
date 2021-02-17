package com.example.demo.core.sender;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CustomMobileSender<REQ, RES>{

    RES R;

    String sendCount(){
        return "custom Mobile Sender Test";
    };
}
