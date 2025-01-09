package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "bi_menu")
@Table(name = "tbl_menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@ToString
public class Menu {

    @Id
    @Column(name = "menu_code")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @JoinColumn(name = "category_code")          // Category 클래스에 있는 category_code 와 조인
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

}
