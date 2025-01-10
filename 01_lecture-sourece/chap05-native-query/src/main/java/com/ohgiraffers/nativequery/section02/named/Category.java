package com.ohgiraffers.nativequery.section02.named;

import jakarta.persistence.*;

@Entity(name = "Section02Category")
@Table(name = "tbl_category")
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "categoryCountAutoMapping2",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}
        )
})
@NamedNativeQueries(value = {
        @NamedNativeQuery(
                name = "Category.menuCountOfCategory",
                query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count" +
                        " FROM tbl_category a" +
                        " LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code" +
                        "               FROM tbl_menu b" +
                        "           GROUP BY b.category_code) v ON (a.category_code = v.category_code)" +
                        " ORDER BY 1",
                resultSetMapping = "categoryCountAutoMapping2"
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
