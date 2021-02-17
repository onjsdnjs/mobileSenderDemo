package com.example.demo.sender;

import com.example.demo.core.sender.MobileSender;
import com.example.demo.dto.KtMobileRequest;
import com.example.demo.dto.KtMobileResponse;
import com.example.demo.sender.custom.CustomMobileSender;
import com.example.demo.sender.custom.MicroServiceMobileSender;

public interface KtMobileSender extends MobileSender<KtMobileRequest, KtMobileResponse>, CustomMobileSender, MicroServiceMobileSender { }
