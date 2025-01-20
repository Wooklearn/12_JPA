package com.ohgiraffers.jpaTest.model;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuMessage {

    private int code;
    private String massage;
    private Map<String, Object> result;

}
