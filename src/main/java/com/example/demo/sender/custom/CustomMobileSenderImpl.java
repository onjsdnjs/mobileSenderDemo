package com.example.demo.sender.custom;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class CustomMobileSenderImpl{

    int sendCount(){
        return 1000;
    };
}
