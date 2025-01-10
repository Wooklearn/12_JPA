package com.ohgiraffers.jpaTest.jpaTest;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menurepository;

    public MenuDTO findByMenu(int menuCode) {

        Menu menu = menurepository.findByMenuCode(menuCode);

        return new MenuDTO(
                menu.getMenuCode(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                menu.getCategoryCode(),
                menu.getOrderableStatus());
    }
    @Transactional
    public Menu saveNewMenu(MenuDTO menuDTO) {

        Menu menu = new Menu();
        menu.setMenuCode(menuDTO.getMenuCode());
        menu.setMenuName(menuDTO.getMenuName());
        menu.setMenuPrice(menuDTO.getMenuPrice());
        menu.setCategoryCode(menuDTO.getCategoryCode());
        menu.setOrderableStatus(menuDTO.getOrderableStatus());

        return menurepository.save(menu); // 저장된 메뉴를 반환
    }

    @Transactional
    public void deleteByMenu(int menuCode) {

        menurepository.deleteByMenuCode(menuCode);

    }

}
