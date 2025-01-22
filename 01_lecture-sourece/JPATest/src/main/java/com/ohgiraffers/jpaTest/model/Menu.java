package com.ohgiraffers.jpaTest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;
}
