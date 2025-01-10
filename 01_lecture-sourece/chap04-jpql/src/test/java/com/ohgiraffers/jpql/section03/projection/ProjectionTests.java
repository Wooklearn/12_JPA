package com.ohgiraffers.jpql.section03.projection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectionTests {

    /*
     * [ 프로젝션(projection) ]
     * SELECT절에 조회할 대상을 지정하는 것을 프로젝션이라고 한다.
     * (SELECT {프로젝션 대상} FROM ...)
     *
     * [ 프로젝션 대상의 4가지 방식 ]
     * 1. 엔티티 프로젝션
     *   원하는 객체를 바로 조회할 수 있다.
     *   조회된 엔티티는 영속성 컨텍스트가 관리한다.
     * 2. 임베디드 타입 프로젝션
     *   엔티티와 거의 비슷하게 사용되며 조회의 시작점이 될 수 없다. (= from절에 사용 불가)
     *   임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다.
     * 3. 스칼라 타입 프로젝션
     *   숫자, 문자, 날짜 같은 기본 데이터 타입이다.
     *   스칼라 타입은 영속성 컨텍스트에서 관리되지 않는다.
     * 4. new 명령어를 활용한 프로젝션
     *   다양한 종류의 단순 값들을 DTO로 바로 조회하는 방식으로 'new 패키지명.DTO명'을 쓰면 해당 DTO로 바로 반환받을 수 있다.
     *   new 명령어를 사용한 클래스의 객체는 엔티티가 아니므로 영속성 컨텍스트에서 관리되지 않는다.
     * */

    @Autowired
    private ProjectionService service;

    @Autowired
    private ProjectionRepository repository;

    /* 1. 엔티티 프로젝션 */
    @DisplayName("단일 엔티티 프로젝션 테스트")
    @Test
    public void testSingleEntityProjection() {
        //given
        //when
//        List<Menu> menuList = repository.singleEntityProjection();
        List<Menu> menuList = service.singleEntityProjection();

        //then
        Assertions.assertNotNull(menuList);
    }

    @DisplayName("양방향 연관관계 엔티티 프로젝션 테스트")
    @Test
    public void testBiDirectionEntityProjection() {
        //given
        int menuCode = 7;

        //when
//        BiDirectionCategory categoryOfMenu = repository.biDirectionEntityProjection(menuCode);
        BiDirectionCategory categoryOfMenu = service.biDirectionEntityProjection(menuCode);

        //then
        Assertions.assertNotNull(categoryOfMenu);
    }

    /* 2. 임베디드 타입 프로젝션 */
    @DisplayName("임베디드 타입 프로젝션 테스트")
    @Test
    public void testEmbeddedTypeProjection() {
        //given
        //when
        List<MenuInfo> menuInfoList = repository.embeddedTypeProjection();

        //then
        Assertions.assertNotNull(menuInfoList);
        menuInfoList.forEach(System.out::println);
    }

    /* 3. 스칼라 타입 프로젝션 */
    @DisplayName("TypedQuery를 이용한 스칼라 타입 프로젝션 테스트")
    @Test
    public void testScalarTypeProjectionByTypedQuery() {
        //given
        //when
        List<String> categoryNameList = repository.scalarTypeProjectionByTypedQuery();

        //then
        Assertions.assertNotNull(categoryNameList);
        categoryNameList.forEach(System.out::println);
    }

    @DisplayName("Query를 이용한 스칼라 타입 프로젝션 테스트")
    @Test
    public void testScalarTypeProjectionByQuery() {
        //given
        //when
        List<Object[]> categoryList = repository.scalarTypeProjectionByQuery();

        //then
        Assertions.assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }

    /* 4. new 명령어를 활용한 프로젝션 */
    @DisplayName("new 명령어를 활용한 프로젝션 테스트")
    @Test
    public void testNewCommandProjection() {
        //given
        //when
        List<CategoryInfo> categoryInfoList = repository.newCommandProjection();

        //then
        Assertions.assertNotNull(categoryInfoList);
        categoryInfoList.forEach(System.out::println);
    }
}
