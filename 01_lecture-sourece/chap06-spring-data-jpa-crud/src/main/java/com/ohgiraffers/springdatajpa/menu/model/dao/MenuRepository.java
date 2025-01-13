package com.ohgiraffers.springdatajpa.menu.model.dao;

import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* comment.
*   JpaRepository 란?
*   EntityManager 와 EntityManagerFactory, EntityTransaction 을
*   구현한 클래스이다. 따라서 이제 우리는 미리 구현 된 클래스를 상속받아
*   더 이상 매니저를 명시적으로 호출 할 필요가 없다.
*   JpaRepository<사용할 엔티티, 해당 엔티티 식별자>
*/

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {  // < 메뉴 엔티티 / PK값 - 인티저 >



}
