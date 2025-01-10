package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.*;

@Entity(name = "Section01Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {
        /* 1. @Column으로 매핑 설정이 되어 있는 경우 사용 (자동 엔티티 매핑) */
        @SqlResultSetMapping(
                name = "categoryCountAutoMapping",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}
        ),
        /* 2. 매핑 설정을 수동으로 하는 경우 사용 (@Column 생략 가능) */
        @SqlResultSetMapping(
                name = "categoryCountManualMapping",
                entities = {
                        @EntityResult(entityClass = Category.class, fields = {
                                @FieldResult(name = "categoryCode", column = "category_code"),
                                @FieldResult(name = "categoryName", column = "category_name"),
                                @FieldResult(name = "refCategoryCode", column = "ref_category_code")
                        })
                },
                columns = {@ColumnResult(name = "menu_count")}
        )
})
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    protected Category() {}

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
