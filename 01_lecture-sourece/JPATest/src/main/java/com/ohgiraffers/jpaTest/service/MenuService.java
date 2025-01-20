package com.ohgiraffers.jpaTest.service;

import com.ohgiraffers.jpaTest.model.Menu;
import com.ohgiraffers.jpaTest.model.MenuDTO;
import com.ohgiraffers.jpaTest.model.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
