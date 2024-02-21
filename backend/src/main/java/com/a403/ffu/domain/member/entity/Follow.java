package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.global.audit.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseTime implements Persistable<FollowId> {

    @EmbeddedId
    private FollowId id;

    public Follow(Member follower, Member followee) {
        this.id = new FollowId(follower, followee);
    }

    @Override
    public boolean isNew() {
        return getCreated() == null;
    }
}
