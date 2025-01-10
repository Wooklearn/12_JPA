package com.ohgiraffers.jpql.section07.subquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubQueryRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> selectWithSubQuery(String categoryName) {
        String jpql = "SELECT m " +
                        "FROM Section07Menu m " +
                        "WHERE m.categoryCode = (SELECT c.categoryCode " +
                                                "FROM Section07Category c " +
                                                "WHERE c.categoryName = :categoryName)";

        List<Menu> menuList = manager.createQuery(jpql, Menu.class)
                                        .setParameter("categoryName", categoryName)
                                        .getResultList();

        return menuList;
    }

    public List<Menu> selectWithSubQuery2(String categoryName) {
        String jpql = "SELECT m" +
                " FROM Section07Menu m" +
                " WHERE EXISTS (SELECT c.categoryCode" +
                                " FROM Section07Category c" +
                                " WHERE m.categoryCode = c.categoryCode" +
                                " AND c.refCategoryCode = (SELECT c2.categoryCode" +
                                                            " FROM Section07Category c2" +
                                                            " WHERE c2.categoryName = :categoryName)" +
                                ")";

        List<Menu> menuList = manager.createQuery(jpql, Menu.class)
                                        .setParameter("categoryName", categoryName)
                                        .getResultList();

        return menuList;
    }
}
