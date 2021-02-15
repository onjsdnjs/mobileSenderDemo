package com.example.demo.controller;

import com.example.demo.dto.*;
//import com.example.demo.sender.AnnotatedMobileSender;
import com.example.demo.sender.KakaoMobileSender;
import com.example.demo.sender.KtMobileSender;
import com.example.demo.sender.NaverMobileSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class MobileSenderController {

    /*@Autowired
    private AnnotatedMobileSender annotatedMobileSender;*/

    @Autowired
    private KakaoMobileSender kakaoMobileSender;

    @Autowired
    private NaverMobileSender naverMobileSender;

    @Autowired
    private KtMobileSender ktMobileSender;

    @PostConstruct
    public void mobileSend() {

        System.out.println("=========================================");

        kakaoMobileSender.send(new KakaoMobileRequest());
        KakaoMobileResponse receive1 = kakaoMobileSender.receive(new KakaoMobileResponse(3L));
        System.out.println("receive.getId: " + receive1.getId());
        System.out.println("");

        naverMobileSender.send(new NaverMobileRequest());
        NaverMobileResponse receive2 = naverMobileSender.receive(new NaverMobileResponse(4L));
        System.out.println("receive.getId: " + receive2.getId());
        System.out.println("");

        ktMobileSender.send(new KtMobileRequest());
        KtMobileResponse receive3 = ktMobileSender.receive(new KtMobileResponse(5L));
        System.out.println("receive.getId: " + receive3.getId());
        System.out.println("=========================================");

       /* annotatedMobileSender.send(new KakaoMobileRequest());
        AnnotatedMobileSender receive1 = annotatedMobileSender.receive(new KakaoMobileResponse(3L));
        System.out.println("receive.getId: " + receive1.getId());
        System.out.println("");*/
    }
}
