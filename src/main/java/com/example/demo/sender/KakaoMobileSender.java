package com.example.demo.sender;

import com.example.demo.core.config.annotation.NoMobileSenderBean;
import com.example.demo.core.sender.MobileSender;
import com.example.demo.dto.KakaoMobileRequest;
import com.example.demo.dto.KakaoMobileResponse;

//@NoMobileSenderBean
public interface KakaoMobileSender extends MobileSender<KakaoMobileRequest, KakaoMobileResponse> { }
