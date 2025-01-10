package com.ohgiraffers.jpql.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupFunctionRepository {

    @PersistenceContext
    private EntityManager manager;

    public long countMenuOfCategory(int categoryCode) {
        String jpql = "SELECT COUNT(m.menuPrice) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
        long countOfMenu = manager.createQuery(jpql, Long.class)
                                    .setParameter("categoryCode", categoryCode)
                                    .getSingleResult();

        return countOfMenu;
    }

    public Long otherWithNoResult(int categoryCode) {
        String jpql = "SELECT SUM(m.menuPrice) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";
//        long sumOfPrice = manager.createQuery(jpql, Long.class)
//                                    .setParameter("categoryCode", categoryCode)
//                                    .getSingleResult();
        Long sumOfPrice = manager.createQuery(jpql, Long.class)
                                    .setParameter("categoryCode", categoryCode)
                                    .getSingleResult();

        return sumOfPrice;
    }

    public List<Object[]> selectByGroupByHaving(long minPrice) {
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice)" +
                        " FROM Section05Menu m" +
                        " GROUP BY m.categoryCode" +
                        " HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList =  manager.createQuery(jpql)
                                                        .setParameter("minPrice", minPrice)
                                                        .getResultList();

        return sumPriceOfCategoryList;
    }
}
