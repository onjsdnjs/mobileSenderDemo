package com.example.demo.sender;

import com.example.demo.core.sender.MobileSender;
import com.example.demo.dto.NaverMobileRequest;
import com.example.demo.dto.NaverMobileResponse;
import com.example.demo.sender.custom.CustomMobileSender;

public interface NaverMobileSender extends MobileSender<NaverMobileRequest, NaverMobileResponse>, CustomMobileSender{

    String sendCount();

}
