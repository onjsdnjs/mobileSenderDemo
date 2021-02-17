package io.anymobi.sender.sender;

import io.anymobi.sender.core.sender.MobileSender;
import io.anymobi.sender.dto.KakaoMobileRequest;
import io.anymobi.sender.dto.KakaoMobileResponse;
import io.anymobi.sender.sender.custom.MicroServiceMobileSender;

public interface KakaoMobileSender extends MobileSender<KakaoMobileRequest, KakaoMobileResponse>, MicroServiceMobileSender { }
