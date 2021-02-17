package com.example.demo.sender;

import com.example.demo.core.sender.MobileSender;
import com.example.demo.dto.KakaoMobileRequest;
import com.example.demo.dto.KakaoMobileResponse;
import com.example.demo.sender.custom.MicroServiceMobileSender;

public interface KakaoMobileSender extends MobileSender<KakaoMobileRequest, KakaoMobileResponse>, MicroServiceMobileSender { }
