package com.ohgiraffers.jpql.section05.groupfunction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GroupFunctionTests {

    /* JPQL의 그룹함수는 COUNT, MAX, MIN, SUM, AVG로 SQL의 그룹함수와 별반 차이가 없다.
     * 단, 몇 가지 주의사항이 있다.
     * 1. 그룹함수의 반환 타입은 결과 값이 정수면 Long, 실수면 Double로 반환된다.
     * 2. 값이 없는 상태에서 count를 제외한 그룹 함수는 null이 되고 count만 0이 된다.
     *    따라서 반환 값을 담기 위해 선언하는 변수 타입을 기본자료형으로 하면, 조회 결과를 언박싱 할 때 NPE가 발생한다.
     * 3. 그룹함수의 반환 자료형은 Long or Double 형이기 때문에 Having 절에서 그룹 함수 결과 값과 비교하기 위한
     *    파라미터 타입은 Long or Double로 해야 한다.
     * */

    @Autowired
    private GroupFunctionRepository repository;

    @DisplayName("특정 카테고리에 등록된 메뉴 수 조회")
    @Test
    public void testCountMenuOfCategory() {
        //given
        int categoryCode = 4;

        //when
        long countOfMenu = repository.countMenuOfCategory(categoryCode);

        //then
        Assertions.assertTrue(countOfMenu >= 0);
        System.out.println("[ countOfMenu ] " + countOfMenu);
    }

    @DisplayName("COUNT를 제외한 다른 그룹 함수의 조회결과가 없는 경우 테스트")
    @Test
    public void testOthersWithNoResult() {
        //given
        int categoryCode = 44;

        //when
//        long sumOfPrice = repository.otherWithNoResult(categoryCode);

        //then
//        Assertions.assertTrue(sumOfPrice >= 0);
        Assertions.assertDoesNotThrow(
                () -> { Long sumOfPrice = repository.otherWithNoResult(categoryCode); }
        );
    }

    @DisplayName("GROUP BY절과 HAVING절을 사용한 조회 테스트")
    @Test
    public void testSelectByGroupbyHaving() {
        //given
//        int minPrice = 50000;
        long minPrice = 50000L;

        //when
        List<Object[]> sumPriceOfCategoryList = repository.selectByGroupByHaving(minPrice);

        //then
        Assertions.assertNotNull(sumPriceOfCategoryList);
        sumPriceOfCategoryList.forEach(
                row -> {
                    for(Object column : row) {
                        System.out.print(column + " ");
                    }
                    System.out.println();
                }
        );
    }
}
