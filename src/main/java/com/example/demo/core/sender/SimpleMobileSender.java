package com.example.demo.core.sender;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SimpleMobileSender<REQ, RES> implements MobileSender<REQ, RES> {

    RES R;

    @Override
    public void send(REQ req) {
        System.out.println(req.toString());

    }

    @Override
    public RES receive(RES R) {
        return R;
    }

    @Override
    public void insert(REQ req) {
        System.out.println(req.toString());
    }

    @Override
    public void update(REQ req) {
        System.out.println(req.toString());
    }

    @Override
    public void delete(REQ req) {
        System.out.println(req.toString());
    }

    @Override
    public RES findById(Long id) {
        return R;
    }

    @Override
    public List<RES> list() {
        return (List<RES>)Collections.EMPTY_LIST;
    }
}
