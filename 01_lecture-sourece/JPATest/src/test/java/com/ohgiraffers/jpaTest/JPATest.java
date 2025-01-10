package com.ohgiraffers.jpaTest;

import com.ohgiraffers.jpaTest.jpaTest.Menu;
import com.ohgiraffers.jpaTest.jpaTest.MenuDTO;
import com.ohgiraffers.jpaTest.jpaTest.MenuService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class JPATest {

    @Autowired
    private MenuService menuService;

    @Test
    @DisplayName("JPA 인터페이스 조회기능")
    void searchMenu() {

        int menuCode = 10;

        MenuDTO menu = menuService.findByMenu(menuCode);

        System.out.println("메뉴 조회" + menu);

        Assertions.assertNotNull(menu);
    }


    private static Stream<Arguments> insertMenu() {
        return Stream.of(
                Arguments.of(30, "아메리카노", 4000, 4, "Y")
        );
    }

    @ParameterizedTest
    @DisplayName("인설트 테스트")
    @MethodSource("insertMenu")
    void newMenu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {

        MenuDTO newMenu = new MenuDTO(menuCode, menuName, menuPrice, categoryCode, orderableStatus);

        // 메뉴 저장
        Menu savedMenu = menuService.saveNewMenu(newMenu);

        // 저장된 메뉴 검증
        Assertions.assertNotNull(savedMenu);
        Assertions.assertEquals(menuCode, savedMenu.getMenuCode());
        Assertions.assertEquals(menuName, savedMenu.getMenuName());
        Assertions.assertEquals(menuPrice, savedMenu.getMenuPrice());
        Assertions.assertEquals(categoryCode, savedMenu.getCategoryCode());
        Assertions.assertEquals(orderableStatus, savedMenu.getOrderableStatus());
    }

    @Test
    @DisplayName("JPA 삭제 테스트")
    void deleteMenu() {

        int menuCode = 21;

        menuService.deleteByMenu(menuCode);

        MenuDTO menu = menuService.findByMenu(menuCode);

    }
}