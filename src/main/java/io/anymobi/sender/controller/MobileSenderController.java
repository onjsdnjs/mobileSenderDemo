package io.anymobi.sender.controller;

//import com.example.demo.sender.AnnotatedMobileSender;
import io.anymobi.sender.dto.*;
import io.anymobi.sender.sender.KakaoMobileSender;
import io.anymobi.sender.sender.KtMobileSender;
import io.anymobi.sender.sender.NaverMobileSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

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

        System.out.println("============================== Custom MobileSender =========================================");

        int sendCount1 = naverMobileSender.sendCount();
        System.out.println("CustomMobileSender : " +  sendCount1);

        List<String> serverNames1 = kakaoMobileSender.getServerNames("128.162.20.1");
        System.out.println("MicroServiceMobileSender : " +  serverNames1);
        System.out.println("");

        int sendCount2 = ktMobileSender.sendCount();
        List<String> serverNames2 = ktMobileSender.getServerNames("192.163.51.3");
        System.out.println("CustomMobileSender & MicroServiceMobileSender : " +  sendCount2 + ", " + serverNames2);
        System.out.println("");

        System.out.println("============================== Default MobileSender =========================================");

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
