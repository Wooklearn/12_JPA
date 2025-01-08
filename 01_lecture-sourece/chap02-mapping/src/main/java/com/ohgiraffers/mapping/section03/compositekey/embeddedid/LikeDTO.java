package com.ohgiraffers.mapping.section03.compositekey.embeddedid;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LikeDTO {

    private int likedMemberNo;
    private int likedBookNo;

}
