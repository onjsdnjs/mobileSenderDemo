package io.anymobi.sender.service;

import io.anymobi.sender.core.sender.MobileSender;
import io.anymobi.sender.dto.KakaoMobileRequest;
import io.anymobi.sender.dto.KakaoMobileResponse;
import io.anymobi.sender.service.custom.MicroServiceMobileSender;

public interface KakaoMobileSender extends MobileSender<KakaoMobileRequest, KakaoMobileResponse>, MicroServiceMobileSender { }
