package com.example.demo.core.sender;

import java.util.List;

public interface CrudSender<REQ, RES> extends Sender<REQ, RES> {

    void insert(REQ req);

    void update(REQ req);

    void delete(REQ req);

    RES findById(Long id);

    List<RES> list();
}
