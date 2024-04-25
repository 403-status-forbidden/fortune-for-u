package com.a403.ffu.member.entity;

import com.a403.ffu.global.audit.BaseTime;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

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
