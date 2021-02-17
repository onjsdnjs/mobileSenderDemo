package com.example.demo.sender.custom;

import com.example.demo.core.config.annotation.Sender;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional(readOnly = true)
@Sender
public class MicroServiceMobileSenderImpl {

    List<String> getServerNames(String id){
     return Arrays.asList(id + " :  server1","server2","server3");
    }
}
