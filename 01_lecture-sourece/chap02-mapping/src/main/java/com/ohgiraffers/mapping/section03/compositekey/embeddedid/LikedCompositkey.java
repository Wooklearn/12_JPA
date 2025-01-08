package com.ohgiraffers.mapping.section03.compositekey.embeddedid;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

@Embeddable
public class LikedCompositkey {

    @Embedded
    private LikedMemberNo likedMemberNo;

    @Embedded
    private LikedBookNo likedBookNo;

    public LikedCompositkey() {}

    public LikedCompositkey(LikedMemberNo likedMemberNo, LikedBookNo likedBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedBookNo = likedBookNo;
    }
}
