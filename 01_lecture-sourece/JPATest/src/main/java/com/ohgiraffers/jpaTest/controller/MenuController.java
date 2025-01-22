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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
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

    //    수정
    @PutMapping("/update")
    public ResponseEntity<?> updateMenu(@RequestBody MenuDTO menu) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("Application", "json", Charset.forName("UTF-8")));

        menuService.updateMenu(menu);

        return ResponseEntity.created(URI.create("/menu/list")).body(new MenuMessage(200, "매뉴수정성공", null));

    }

    // 상세조회
    @GetMapping("/detail/{menuCode}")
    public ResponseEntity<MenuMessage> menuDetail(@PathVariable("menuCode") int menuCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("Application", "json", Charset.forName("UTF-8")));

        MenuDTO menu = menuService.menuDetail(menuCode);

        Map<String, Object> res = new HashMap<>();

        System.out.println("res = " + res);

        res.put("menuCode", menu);
        return ResponseEntity.ok().headers(headers).body(new MenuMessage(200, "메뉴 조회 성공", res));

    }

    // 메뉴 추가
    @PostMapping("/regist")
    public ResponseEntity<?> menuRegist(@RequestBody MenuDTO menu) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("Application", "json", Charset.forName("UTF-8")));

        menuService.menuRegist(menu);

        return ResponseEntity.created(URI.create("/menu/list")).body(new MenuMessage(200, "메뉴등록", null));

    }

    // 메뉴 삭제
    @DeleteMapping("delete/{menuCode}")
    public ResponseEntity<?> menuDelete(@PathVariable("menuCode") int menuCode) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("Application", "json", Charset.forName("UTF-8")));

        menuService.menuDelete(menuCode);

        return ResponseEntity.created(URI.create("/menu/list")).body(new MenuMessage(200, "메뉴 삭제 성공", null));
    }
}
