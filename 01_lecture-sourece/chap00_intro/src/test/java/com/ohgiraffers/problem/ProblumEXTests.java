package com.ohgiraffers.problem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProblumEXTests {

    /* title. 테스트 코드 기반으로 JPA 를 사용하지 않을 때 문제 EX */

    /* comment.
    *   테스트 전용 클래스란?
    *   @Test 라는 어노테이션이 1개 이상 가지고 있는 클래스를 의미한다.
    *   테스트 메소드는 반환값이 없으며, 따라서 void 형으로 작성한다.
    *   독립적인 실행이 가능하며, 기능을 테스트 하는 것에 초점이 있다.
    *   또한, 접근제한자는 사용하지 않아도 되지만, private 는 안된다.
    *  */

    private Connection con;

    // 우리가 작성한 Test 메소드 실행 전에 1번씩 동작을 하는 역할
    @BeforeEach
    void setConnection() throws SQLException, ClassNotFoundException {
        // JDBC Connection 을 만들기 위한 DB 정보
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menudb";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        // BeforeEach 가 동작하는 지 확인용 sout
        System.out.println("BeforeEach 동작하니?");

        // DB 와 접속하기 위한 통로에 우리 DB 정보 전달
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        System.out.println("AfterEach 동작하니?");
        con.rollback();
        con.close();
    }

    /* comment.
    *   JDBC 를 직접적으로 사용 시 문제점 확인
    *   1. 데이터 변환, SQL 문 작성, JDBC 코드 중복 작성 문제
    *   - 개발 시간 증가, 불필요한 코드 많음, 유지보수성 악화
    *  */

}
