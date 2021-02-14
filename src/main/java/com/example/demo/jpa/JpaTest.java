package com.example.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaTest implements ApplicationRunner {

    @Autowired
    private TestJpa testJpa;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Test test = new Test();
        test.setName("onjsdnjs");

        testJpa.save(test);
    }
}
