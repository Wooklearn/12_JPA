package com.ohgiraffers.jpql.section04.paging;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager manager;

    public List<Menu> usingPagingAPI(int offset, int limit) {
        String jpql = "SELECT m FROM Section04Menu m ORDER BY m.menuCode DESC";
        List<Menu> pagingMenuList = manager.createQuery(jpql, Menu.class)
                                            .setFirstResult(offset)
                                            .setMaxResults(limit)
                                            .getResultList();

        return pagingMenuList;
    }
}
