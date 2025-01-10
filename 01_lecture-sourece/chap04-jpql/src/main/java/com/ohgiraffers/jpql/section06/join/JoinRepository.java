package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> selectByInnerJoin() {
        String jpql = "SELECT m FROM Section06Menu m JOIN m.category c";
        List<Menu> menuList = manager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }

    public List<Object[]> selectByOuterJoin() {
        String jpql = "SELECT m.menuName, c.categoryName" +
                        " FROM Section06Menu m" +
                        " RIGHT JOIN m.category c" +
                        " ORDER BY m.category.categoryCode";
        List<Object[]> menuList = manager.createQuery(jpql).getResultList();

        return menuList;
    }

    public List<Object[]> selectByCollectionJoin() {
        String jpql = "SELECT m.menuName, c.categoryName" +
                        " FROM Section06Category c" +
                        " LEFT JOIN c.menuList m";
        List<Object[]> categoryList = manager.createQuery(jpql).getResultList();

        return categoryList;
    }

    public List<Object[]> selectByThetaJoin() {
        String jpql = "SELECT c.categoryName, m.menuName FROM Section06Category c, Section06Menu m";
        List<Object[]> categoryList = manager.createQuery(jpql).getResultList();

        return categoryList;
    }

    public List<Menu> selectByFetchJoin() {
        String jpql = "SELECT m FROM Section06Menu m JOIN FETCH m.category c";
        List<Menu> menuList = manager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }
}
