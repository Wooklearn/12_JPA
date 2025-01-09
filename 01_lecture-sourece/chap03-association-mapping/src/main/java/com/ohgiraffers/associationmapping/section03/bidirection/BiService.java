package com.ohgiraffers.associationmapping.section03.bidirection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiService {

    @Autowired
    private BiRepository repository;

    public Menu findMenu(int menuCode) {

        return repository.findMenu(menuCode);

    }
}
