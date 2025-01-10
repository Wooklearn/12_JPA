package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class NativeQueryTests {

    @PersistenceContext
    private EntityManager manager;

    /* 1. 결과 타입을 정의한 경우 */
    @DisplayName("결과 타입을 정의한 Native Query 사용 테스트")
    @Test
    @Transactional
    public void testNativeQueryByResultType() {
        //given
        int menuCode = 15;

        //when
        String query = "SELECT menu_code, menu_name, menu_price, category_code, orderable_status" +
                        " FROM tbl_menu" +
                        " WHERE menu_code = ?";
        Query nativeQuery = manager.createNativeQuery(query, Menu.class).setParameter(1, menuCode);
        Menu foundMenu = (Menu) nativeQuery.getSingleResult();

        //then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertTrue(manager.contains(foundMenu));
        System.out.println(foundMenu);
    }

    /* 2. 결과 타입을 정의할 수 없는 경우 */
    @DisplayName("결과 타입을 지정할 수 없는 Native Query 조회 테스트")
    @Test
    public void testNativeQueryByNoResultType() {
        //given
        //when
        String query = "SELECT menu_name, menu_price FROM tbl_menu";
        List<Object[]> menuList = manager.createNativeQuery(query).getResultList();

        //then
        Assertions.assertNotNull(menuList);
        menuList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + " ");
                    }
                    System.out.println();
                }
        );
    }

    /* 3. 결과 매핑을 사용하는 경우 */
    @DisplayName("자동 결과 매핑을 사용한 Native Query 조회 테스트")
    @Test
    @Transactional
    public void testNativeQueryByAutoMapping() {
        //given
        //when
        String query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                        " FROM tbl_category a" +
                        " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                                    " FROM tbl_menu b" +
                                    " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                        " ORDER BY 1";

        Query nativeQuery = manager.createNativeQuery(query, "categoryCountAutoMapping");
        List<Object[]> categoryList = nativeQuery.getResultList();

        //then
        Assertions.assertNotNull(categoryList);
        Assertions.assertTrue(manager.contains(categoryList.get(0)[0]));
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + "/");
                    }
                    System.out.println();
                }
        );
    }

    @DisplayName("수동 결과 매핑을 사용한 Native Query 조회 테스트")
    @Test
    @Transactional
    public void testNativeQueryByManualMapping() {
        //given
        //when
        String query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                        " FROM tbl_category a" +
                        " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                                    " FROM tbl_menu b" +
                                    " GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                                    " ORDER BY 1";

        Query nativeQuery = manager.createNativeQuery(query, "categoryCountManualMapping");
        List<Object[]> categoryList = nativeQuery.getResultList();

        //then
        Assertions.assertNotNull(categoryList);
        Assertions.assertTrue(manager.contains(categoryList.get(0)[0]));
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + "/");
                    }
                    System.out.println();
                }
        );
    }

}
