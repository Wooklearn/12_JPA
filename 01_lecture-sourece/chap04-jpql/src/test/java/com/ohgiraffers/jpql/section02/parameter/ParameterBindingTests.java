package com.ohgiraffers.jpql.section02.parameter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ParameterBindingTests {

    /*
     * [ 파라미터 바인딩 방법 ]
     * 1. 이름 기준 파라미터(named parameters)
     *   ':' 다음에 이름 기준 파라미터를 지정한다.
     * 2. 위치 기준 파라미터(positional parameters)
     *   '?' 다음에 값을 주고 위치 값은 1부터 시작한다.
     * */

    @Autowired
    private ParameterBindingRepository repository;

    @DisplayName("이름 기준 파라미터 바인딩 메뉴 목록 조회 테스트")
    @Test
    public void testParameterBindingByName() {
        //given
        String menuName = "한우딸기국밥";

        //when
        List<Menu> menuList = repository.selectMenuByBindingName(menuName);

        //then
        Assertions.assertEquals(menuName, menuList.get(0).getMenuName());
    }

    @DisplayName("위치 기준 파라미터 바인딩 메뉴 목록 조회 테스트")
    @Test
    public void testParameterBindingByPosition() {
        //given
        String menuName = "한우딸기국밥";

        //when
        List<Menu> menuList = repository.selectMenuByBindingPosition(menuName);

        //then
        Assertions.assertEquals(menuName, menuList.get(0).getMenuName());
    }
}
