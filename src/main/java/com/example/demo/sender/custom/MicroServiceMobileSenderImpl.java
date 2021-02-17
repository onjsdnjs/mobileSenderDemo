package com.example.demo.sender.custom;

import java.util.Arrays;
import java.util.List;

public class MicroServiceMobileSenderImpl {

    List<String> getServerNames(){
     return Arrays.asList("server1","server2","server3");
    }
}
