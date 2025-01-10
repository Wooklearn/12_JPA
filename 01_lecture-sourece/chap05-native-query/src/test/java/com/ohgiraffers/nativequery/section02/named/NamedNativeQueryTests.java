package com.ohgiraffers.nativequery.section02.named;

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
public class NamedNativeQueryTests {

    @PersistenceContext
    private EntityManager manager;

    @DisplayName("NamedNativeQuery를 이용한 조회 테스트")
    @Test
    @Transactional
    public void testSelectByNamedNativeQuery() {
        //given
        //when
        Query nativeQuery = manager.createNamedQuery("Category.menuCountOfCategory");
//                                    .setFirstResult(10)
//                                    .setMaxResults(5);
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
