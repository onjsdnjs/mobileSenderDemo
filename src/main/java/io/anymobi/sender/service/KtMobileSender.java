package io.anymobi.sender.service;

import io.anymobi.sender.core.sender.MobileSender;
import io.anymobi.sender.dto.KtMobileRequest;
import io.anymobi.sender.dto.KtMobileResponse;
import io.anymobi.sender.service.custom.CustomMobileSender;
import io.anymobi.sender.service.custom.MicroServiceMobileSender;

public interface KtMobileSender extends MobileSender<KtMobileRequest, KtMobileResponse>, CustomMobileSender, MicroServiceMobileSender { }
