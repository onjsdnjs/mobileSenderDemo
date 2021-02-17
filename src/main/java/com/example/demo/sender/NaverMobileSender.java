package com.example.demo.sender;

import com.example.demo.core.sender.MobileSender;
import com.example.demo.dto.NaverMobileRequest;
import com.example.demo.dto.NaverMobileResponse;

public interface NaverMobileSender extends MobileSender<NaverMobileRequest, NaverMobileResponse> {

    String sendCount();

}
