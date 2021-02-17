package io.anymobi.sender.service;

import io.anymobi.sender.core.sender.MobileSender;
import io.anymobi.sender.dto.NaverMobileRequest;
import io.anymobi.sender.dto.NaverMobileResponse;
import io.anymobi.sender.service.custom.CustomMobileSender;

public interface NaverMobileSender extends MobileSender<NaverMobileRequest, NaverMobileResponse>, CustomMobileSender {
}
