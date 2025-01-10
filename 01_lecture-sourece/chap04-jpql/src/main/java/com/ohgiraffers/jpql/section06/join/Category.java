package com.ohgiraffers.jpql.section06.join;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Section06Category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category")
    private List<Menu> menuList;

    protected Category() {}

    public List<Menu> getMenuList() {
        return menuList;
    }
}
