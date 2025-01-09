package com.ohgiraffers.associationmapping.section01.manytoone;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {

    // 필드에 private 쓰는 이유는 캡슐화
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

}