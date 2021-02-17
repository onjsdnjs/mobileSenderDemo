package com.example.demo.core.sender;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import com.example.demo.core.config.annotation.Sender;

@Sender
@Transactional(readOnly = true)
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
    @Transactional
    public void insert(REQ req) {
        System.out.println(req.toString());
    }

    @Override
    @Transactional
    public void update(REQ req) {
        System.out.println(req.toString());
    }

    @Override
    @Transactional
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
