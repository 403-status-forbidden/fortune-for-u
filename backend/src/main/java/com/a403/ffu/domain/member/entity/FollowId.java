package com.ssafy.a403.domain.member.entity;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class FollowId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private Member follower;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "followee_id")
    private Member followee;

    public FollowId(Member follower, Member followee) {
        this.follower = follower;
        this.followee = followee;
    }
}
