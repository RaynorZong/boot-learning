package com.demo.service;

import com.demo.model.node.Htxx;

public interface IHtxxService {

    Htxx add(Htxx htxx);

    Htxx addHtxxByName(String name);

    Htxx getHtxxByName(String name);

    boolean deleteHtxx(Long id);

}
