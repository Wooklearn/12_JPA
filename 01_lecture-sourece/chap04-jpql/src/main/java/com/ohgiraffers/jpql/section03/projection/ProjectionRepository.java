package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectionRepository {

    /* index. 3. 학습목표
    *   1. 조회 시 테이블에 대한 설정.
    *   2. 우리는 MYSQL 을 직접적으로 사용하는 것이 아닌 엔티티 객체를 관리하는 곳에서 조회를 한다.
    *  */

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> singleEntityProjection() {
        String jpql = "SELECT m FROM Section03Menu m";
        List<Menu> menuList = manager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }

    public Menu findMenu(int menuCode) {
        return manager.find(Menu.class, menuCode);
    }

    public BiDirectionCategory biDirectionEntityProjection(int menuCode) {
        String jpql = "SELECT m.category FROM BiDirectionMenu m WHERE m.menuCode = :menuCode";
        BiDirectionCategory resultCategory = manager.createQuery(jpql, BiDirectionCategory.class)
                                                    .setParameter("menuCode", menuCode)
                                                    .getSingleResult();

        return resultCategory;
    }

    public List<MenuInfo> embeddedTypeProjection() {
        String jpql = "SELECT m.menuInfo FROM EmbeddedMenu m";
        List<MenuInfo> resultMenuInfo = manager.createQuery(jpql, MenuInfo.class).getResultList();

        return resultMenuInfo;
    }

    public List<String> scalarTypeProjectionByTypedQuery() {
        String jpql = "SELECT c.categoryName FROM Section03Category c";
        List<String> resultList = manager.createQuery(jpql, String.class).getResultList();

        return resultList;
    }

    public List<Object[]> scalarTypeProjectionByQuery() {
        String jpql = "SELECT c.categoryCode, c.categoryName FROM Section03Category c";
        List<Object[]> resultList = manager.createQuery(jpql).getResultList();

        return resultList;
    }

    public List<CategoryInfo> newCommandProjection() {
        String jpql = "SELECT new com.ohgiraffers.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName)" +
                        "FROM Section03Category c";
        List<CategoryInfo> resultList = manager.createQuery(jpql, CategoryInfo.class).getResultList();

        return resultList;
    }
}

