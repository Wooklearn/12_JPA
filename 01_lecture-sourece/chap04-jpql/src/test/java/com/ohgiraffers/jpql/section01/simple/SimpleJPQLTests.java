package com.ohgiraffers.jpql.section01.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SimpleJPQLTests {

    /*
     * [ JPQL(Java Persistence Query Language) ]
     * JPQL은 엔티티 객체를 중심으로 개발할 수 있는 객체 지향 쿼리이다.
     * SQL보다 간결하며 특정 DBMS에 의존하지 않는다.
     * 방언을 통해 해당 DBMS에 맞는 SQL을 실행한다.
     * JPQL은 find() 메소드를 통한 조회와 다르게 항상 데이터베이스에 SQL을 실행해서 결과를 조회한다.
     * */

    /*
     * [ JPA의 공식 지원 기능 ]
     * - JPQL
     * - Criteria Query : JPQL 편하게 작성하도록 도와주는 API
     * - Native SQL : JPA에서 JPQL 대신 직접 SQL을 사용
     *
     * [ JPA의 비공식 지원 기능 ]
     * - QueryDSL : Criteria Query처럼 JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음
     * - JOOQ
     * - JDBC 직접 사용 또는 MyBatis 같은 SQL 매퍼 프레임워크 혼용
     * */

    /*
     * [ JPQL의 기본 문법 (CRUD) ]
     * - select :
     *       select절
     *       from절
     *       [where절]
     *       [group by절]
     *       [having절]
     *       [order by절]
     * - insert : EntityManager가 제공하는 persist() 메소드를 사용하면 되므로 따로 없다.
     * - update :
     *       update
     *       set
     *       [where절]
     * - delete :
     *       delete
     *       from
     *       [where절]
     * */

    /*
     * [ JPQL 사용 시 주의사항 ]
     * - 엔티티와 속성은 대소문자를 구분한다. (userId != userid)
     * - SELECT, FROM, WHERE 같은 JPQL의 기본 키워드들은 대소문자를 구분하지 않는다.
     *   (INSERT 는 persist() 메소드를 사용하면 된다.)
     * - 엔티티명은 클래스명이 아닌 엔티티명이다.
     * - JPQL은 엔티티 별칭을 필수로 사용해야 하며, 별칭 없이 사용하면 에러가 발생한다.
     * */

    /*
     * [ JPQL 사용 방법 ]
     * 1. 작성한 JPQL(문자열)을 entityManager.createQuery() 메소드를 통해 쿼리 객체로 만든다.
     * 2. 쿼리 객체는 TypedQuery, Query 두 가지가 있다.
     *   1) TypedQuery : 반환할 타입을 명확하게 지정하는 방식일 때 사용하며 쿼리 객체의 메소드 실행 결과로 지정한 타입이 반환된다.
     *   2) Query : 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는 Object[]이 반환된다.
     * 3. 쿼리 객체에서 제공하는 메소드 getSingleResult() 또는 getResultList()를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
     *   1) getSingleResult() : 결과가 정확히 한 행인 경우 사용하며, 없거나 많으면 예외가 발생한다.
     *   2) getResultList() : 결과가 2개 행 이상인 경우 사용하며, 컬렉션을 반환한다. 결과가 없으면 빈 컬렉션을 반환한다.
     *   *) 주의사항
     *       - 조회 결과가 단일 컬럼인 경우 Object로 다운캐스팅 해서 사용하지만
     *       - 조회 결과가 다중(여러) 컬럼인 경우 Object[]로 다운캐스팅 해서 사용해야 한다.
     * */

    @Autowired
    private SimpleJPQLRepository repository;

    @DisplayName("TypedQuery를 이용한 단일메뉴(단일행,단일컬럼) 조회 테스트")
    @Test
    public void testSelectSingleMenuByTypedQuery() {
        //given
        //when
        String menuName = repository.selectSingleMenuByTypedQuery();

        //then
        Assertions.assertEquals("한우딸기국밥", menuName);
        Assertions.assertEquals(repository.findMenu(8).getMenuName(), menuName);
    }

    @DisplayName("Query를 이용한 단일메뉴(단일행,단일컬럼) 조회 테스트")
    @Test
    public void testSelectSingleMenuByQuery() {
        //given
        //when
        Object menuName = repository.selectSingleMenuByQuery();

        //then
        Assertions.assertEquals("한우딸기국밥", menuName);
        Assertions.assertTrue(menuName instanceof String);
    }

    @DisplayName("TypedQuery를 이용한 단일행 조회 테스트")
    @Test
    public void testSelectSingleRowByTypedQuery() {
        //given
        //when
        Menu menu = repository.selectSingleRowByTypedQuery();

        //then
        Assertions.assertEquals(8, menu.getMenuCode());
    }

    @DisplayName("TypedQuery를 이용한 다중행 조회 테스트")
    @Test
    public void testSelectMultipleRowByTypedQuery() {
        //given
        //when
        List<Menu> menuList = repository.selectMultipleRowByTypedQuery();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("Query를 이용한 다중행 조회 테스트")
    @Test
    public void testSelectMultipleRowByQuery() {
        //given
        //when
        List<Menu> menuList = repository.selectMultipleRowByQuery();

        //then
        Assertions.assertNotNull(menuList);

        menuList.forEach(System.out::println);
        // 람다 & 스트림 문법으로서 식을 더욱 간결하게 만들어준다.
        // menuList.forEach(menu -> System.out.println(menu));
    }

    /* WHERE절에서 사용하는 연산자는 SQL과 다르지 않음 */
    @DisplayName("DISTINCT를 활용한 중복 제거 다중행 조회 테스트")
    @Test
    public void testSelectUsingDistinct() {
        //given
        //when
        List<Integer> categoryCodeList = repository.selectUsingDistinct();

        //then
        Assertions.assertNotNull(categoryCodeList);
        categoryCodeList.forEach(System.out::println);
    }

    @DisplayName("IN 연산자를 활용한 조회 테스트")
    @Test
    public void testSelectUsingIn() {
        //given
        //when
        List<Menu> menuList = repository.selectUsingIn();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @DisplayName("LIKE 연산자를 활용한 조회 테스트")
    @Test
    public void testSelectUsingLike() {
        //given
        //when
        List<Menu> menuList = repository.selectUsingLike();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }
}
