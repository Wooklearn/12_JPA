package com.ohgiraffers.jpql.section08.namedquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NamedQueryTests {

    /*
     * [ 동적쿼리 ]
     * 현재 우리가 사용하는 방식처럼 EntityManger가 제공하는 메소드를 이용하여
     * JPQL을 문자열로 런타임 시점에 동적으로 쿼리를 만드는 방식
     * (동적으로 만들어질 쿼리를 위한 조건식이나 반복문은 자바 코드를 이용할 수 있음)
     *
     * [ 정적쿼리 ]
     * 미리 쿼리를 정의한 뒤 변경하지 않고 사용하는 쿼리를 말하며 미리 정의한 코드는 이름을 부여해서 사용
     * (= NamedQuery)
     * */

    @Autowired
    private NamedQueryRepository repository;

    @DisplayName("동적쿼리를 이용한 조회 테스트")
    @Test
    public void testSelectByDynamicQuery() {
        //given
        String searchName = "열무김치라떼";
        int searchCode = 8;

        //when
        List<Menu> menuList = repository.selectByDynamicQuery(searchName, searchCode);

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("NamedQuery를 이용한 조회 테스트")
    @Test
    public void testSelectByNamedQuery() {
        //given
        //when
        List<Menu> menuList = repository.selectByNamedQuery();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("xml 기반 NamedQuery를 이용한 조회 테스트")
    @Test
    public void testSelectByNamedQueryWithXml() {
        //given
        int menuCode = 20;

        //when
        Menu foundMenu = repository.selectByNamedQueryWithXml(menuCode);

        //then
        Assertions.assertNotNull(foundMenu);
        System.out.println(foundMenu);
    }
}
