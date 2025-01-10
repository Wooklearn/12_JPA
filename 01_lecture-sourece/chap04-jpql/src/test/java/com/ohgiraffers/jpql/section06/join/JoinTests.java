package com.ohgiraffers.jpql.section06.join;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JoinTests {

    /*
     * [ 조인의 종류 ]
     * 1. 일반 조인 : 일반적인 SQL 조인 (내부 조인, 외부 조인, 컬렉션 조인, 세타 조인)
     * 2. 페치 조인 : JPQL에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한번에 조회한다.
     *              지연 로딩이 아닌 즉시 로딩을 수행하며 join fetch 명령어를 사용한다.
     * */

    @Autowired
    private JoinRepository repository;

    @DisplayName("내부조인을 이용한 조회 테스트")
    @Test
    public void testSelectByInnerJoin() {
        //given
        //when
        List<Menu> menuList = repository.selectByInnerJoin();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("외부조인을 이용한 조회 테스트")
    @Test
    public void testSelectByOuterJoin() {
        //given
        //when
        List<Object[]> menuList = repository.selectByOuterJoin();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("컬렉션조인을 이용한 조회 테스트")
    @Test
    public void testSelectByCollectionJoin() {
        //given
        //when
        List<Object[]> categoryList = repository.selectByCollectionJoin();

        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("세타조인을 이용한 조회 테스트")
    @Test
    public void testSelectByThetaJoin() {
        //given
        //when
        List<Object[]> categoryList = repository.selectByThetaJoin();

        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("페치조인을 이용한 조회 테스트")
    @Test
    public void testSelectByFetchJoin() {
        //given
        //when
        List<Menu> menuList = repository.selectByFetchJoin();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
}
