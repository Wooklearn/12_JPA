package com.ohgiraffers.jpql.section04.paging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PagingTests {

    /* 페이징 처리용 SQL은 DBMS에 따라 각각 문법이 다른 문제점을 가지고 있다.
     * JPA는 이러한 페이징을 API를 통해 추상화하여 간단하게 처리할 수 있도록 제공한다.
     *
     * - setFirstResult(int startPosition) : 조회를 시작할 위치(0부터 시작)
     * - setMaxResults(int maxResult) : 조회할 데이터 수
     * */

    @Autowired
    private PagingRepository repository;

    @DisplayName("페이징 API를 이용한 조회 테스트")
    @Test
    public void testUsingPagingAPI() {
        //given
        int offset = 10;     // 조회를 건너뛸 행 수
        int limit = 5;       // 조회할 최대 행 수

        //when
        List<Menu> menuList = repository.usingPagingAPI(offset, limit);

        //then
        Assertions.assertTrue(menuList.size() > 0 && menuList.size() < 6);
        menuList.forEach(System.out::println);
    }
}
