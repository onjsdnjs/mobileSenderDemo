package io.anymobi.sender.controller;

import io.anymobi.sender.core.config.annotation.MobileSenderDefinition;
import org.springframework.stereotype.Service;

@Service
public class AopServiceTest {

    @MobileSenderDefinition
    public String aopTest(){
        return "aopTest";
    }
}
