package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/*
 *  [ 임베디드 타입(embedded type, 복합 값 타입 또는 내장 타입) ]
 *  새로운 값 타입을 직접 정의한 것으로 주로 기본 값 타입을 모아서 만든 하나의 타입을 말한다.
 *  엔티티의 필드 중 일부분을 하나의 임베디드 타입으로 정의하면 알아보기 쉽고,
 *  재사용성이 높게 디자인 할 수 있어 유지보수에 용이하다.
 * */
@Embeddable
public class MenuInfo {

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    protected MenuInfo() {}

    public MenuInfo(String menuName, int menuPrice) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                '}';
    }
}
