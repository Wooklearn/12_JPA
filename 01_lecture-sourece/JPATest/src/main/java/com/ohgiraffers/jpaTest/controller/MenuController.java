package com.ohgiraffers.jpaTest.controller;

import com.ohgiraffers.jpaTest.model.MenuDTO;
import com.ohgiraffers.jpaTest.model.MenuMessage;
import com.ohgiraffers.jpaTest.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController( MenuService menuService) {
        this.menuService=menuService;
    }

    @GetMapping("/list")
    public ResponseEntity<MenuMessage> menuList() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("Application", "json", Charset.forName("UTF-8")));

        List<MenuDTO> menuList = menuService.menuList();

        Map<String, Object> res = new HashMap<>();

        res.put("res", menuList);

        System.out.println("전체메뉴조회 = " + res);
        System.out.println("메뉴리스트 = " + menuList);

        return ResponseEntity.ok().headers(headers).body(new MenuMessage(200, "메뉴 전체조회 성공!!!", res));




    }


}
