package com.ohgiraffers.jpaTest.service;

import com.ohgiraffers.jpaTest.model.Menu;
import com.ohgiraffers.jpaTest.model.MenuDTO;
import com.ohgiraffers.jpaTest.model.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public List<MenuDTO> menuList() {

        List<Menu> menuList = menuRepository.findAll();

        return menuList.stream().map(menuKing -> modelMapper.map(menuKing, MenuDTO.class)).collect(Collectors.toList());
    }

    public void updateMenu(MenuDTO menu) {

        Menu menu1 = menuRepository.findById(menu.getMenuCode()).orElseThrow(IllegalArgumentException::new);

        Menu menu2 = menu1.toBuilder().menuName(menu1.getMenuName()).menuPrice(menu1.getMenuPrice()).build();

        menuRepository.save(menu2);


    }

    public MenuDTO menuDetail(int menuCode) {

        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(menu, MenuDTO.class);



    }

    @Transactional
    public void menuRegist(MenuDTO menu) {

        menuRepository.save(modelMapper.map(menu, Menu.class));

    }

    public void menuDelete(int menuCode) {

        menuRepository.deleteById(menuCode);

        modelMapper.map(menuCode, MenuDTO.class);
    }
}
