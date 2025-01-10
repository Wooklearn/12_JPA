package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.*;

@Entity(name = "BiDirectionMenu")
@Table(name = "tbl_menu")
public class BiDirectionMenu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private BiDirectionCategory category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected BiDirectionMenu() {}
}
