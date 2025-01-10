package com.ohgiraffers.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    /* index. 2. 학습목표
    *   1. 쿼리문에 우리가 지정한 값을 넣고 싶을 때
    *   2. : , ? 의 차이점 고찰
    *   3. 쿼리문에 변수를 집어 넣어야 하는 이유에 대해 고찰
    *  */

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> selectMenuByBindingName(String menuName) {
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = :menuName";
        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                                            .setParameter("menuName", menuName)
                                            .getResultList();

        return resultMenuList;
    }

    public List<Menu> selectMenuByBindingPosition(String menuName) {
        String jpql = "SELECT m FROM Section02Menu m WHERE m.menuName = ?1";
        List<Menu> resultMenuList = manager.createQuery(jpql, Menu.class)
                                            .setParameter(1, menuName)
                                            .getResultList();

        return resultMenuList;
    }
}
