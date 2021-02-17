package com.example.demo.sender.custom;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CustomMobileSenderImpl{

    String sendCount(){
        return "custom Mobile Sender Test";
    };
}
