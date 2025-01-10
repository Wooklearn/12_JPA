package com.ohgiraffers.jpql.section07.subquery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubQueryTests {

    /* JPQL도 SQL처럼 서브쿼리를 지원한다.
     * 하지만, SELECT, FROM절에서는 사용할 수 없고 WHERE, HAVING절에서만 사용이 가능하다.
     * */

    @Autowired
    private SubQueryRepository repository;

    @DisplayName("서브쿼리를 이용한 메뉴 조회 테스트")
    @Test
    public void testSelectWithSubQuery() {
        //given
        String categoryName = "한식";

        //when
        List<Menu> menuList = repository.selectWithSubQuery(categoryName);

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("다중행 다중열 서브쿼리를 이용한 메뉴 조회 테스트")
    @Test
    public void testSelectWithSubQuery2() {
        /* 단일행 단일열의 결과가 나오는 서브쿼리가 아닌 이상 일반 비교연산자 사용이 불가능하다.
         *
         * [NOT] EXISTS (sub query)
         * {ALL | ANY | SOME} (sub query)
         * [NOT] IN (sub query)
         * */

        //given
        String categoryName = "식사";

        //when
        List<Menu> menuList = repository.selectWithSubQuery2(categoryName);

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
}
