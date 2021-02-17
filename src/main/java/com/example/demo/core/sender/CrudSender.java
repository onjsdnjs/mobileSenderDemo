package com.example.demo.core.sender;

import com.example.demo.core.config.annotation.NoMobileSenderBean;

import java.util.List;

@NoMobileSenderBean
public interface CrudSender<REQ, RES> extends Sender<REQ, RES> {

    void insert(REQ req);

    void update(REQ req);

    void delete(REQ req);

    RES findById(Long id);

    List<RES> list();
}
