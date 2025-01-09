package com.ohgiraffers.associationmapping.section03.bidirection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BiTest {

    @Autowired
    private BiService service;

    /* comment.
    *   데이터베이스의 테이블은 외래키 하나로 양방향
    *   조회가 가능하다.   (JOIN)
    *   하지만, 객체는 서로 다른 두 단방향 참조를
    *   합쳐서 양방향 이라고 한다.
    *   따라서 두 개의 연관관계 중 주인을 설정*(mapped by)
    *   즉 주인을 설정하는 기준은
    *   외래키를 관리하는(가지고 있는) 엔티티가 주인이
    *   되어야 한다. 따라서 우리가 쓰는 테이블 따지면
    *   Menu 가 categoryCode 를 가지기 때문에 주인이다.
    *  */

    @DisplayName("양방향 연관관계 매핑 조회")
    @Test
    void ownerFindTest() {
        int menuCode = 10;

        Menu foundMenu = service.findMenu(menuCode);

        Assertions.assertEquals(menuCode,foundMenu.getMenuCode());
    }


}
