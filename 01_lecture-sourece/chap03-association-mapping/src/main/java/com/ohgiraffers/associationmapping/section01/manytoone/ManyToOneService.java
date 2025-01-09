package com.ohgiraffers.associationmapping.section01.manytoone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManyToOneService {

    @Autowired
    private ManyToOneRepository repository;
    @Autowired
    private ManyToOneRepository manyToOneRepository;


    public Menu findMenu(int menuCode) {

        return repository.find(menuCode);

    }

    public String findCategoryName(int menuCode) {

        return repository.findCategoryName(menuCode);

    }

    @Transactional
    public void registMenu(MenuDTO menuInfo) {

        Menu menu = new Menu(
                menuInfo.getMenuCode(),
                menuInfo.getMenuName(),
                menuInfo.getMenuPrice(),
                new Category(
                        menuInfo.getCategory().getCategoryCode(),
                        menuInfo.getCategory().getCategoryName(),
                        menuInfo.getCategory().getRefCategoryCode()
                ),

                menuInfo.getOrderableStatus()
        );

        manyToOneRepository.regist(menu);

    }
}
