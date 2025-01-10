package com.ohgiraffers.jpql.section03.projection;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BiDirectionCategory")
@Table(name = "tbl_category")
public class BiDirectionCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category")
    private List<BiDirectionMenu> menuList;

    protected BiDirectionCategory() {}

    public List<BiDirectionMenu> getMenuList() {
        return menuList;
    }
}
