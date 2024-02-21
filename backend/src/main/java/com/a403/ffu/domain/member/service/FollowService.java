package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Follow;
import com.ssafy.a403.domain.member.entity.FollowId;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void follow(Member follower, Member followee) {
        if (isFollowedCounselor(follower, followee)) {
            log.info("한 명의 상담가를 두 번 이상 팔로우 할 수 없습니다.");
            return;
        }
        followRepository.save(new Follow(follower, followee));
    }

    @Transactional
    public void unfollow(Member follower, Member followee) {

        if (!isFollowedCounselor(follower, followee)) {
            log.info("해당 상담가를 아직 팔로우하지 않았습니다.");
            return;
        }
        followRepository.deleteById(new FollowId(follower, followee));
    }

    public Boolean isFollowedCounselor(Member follower, Member followee) {
        return followRepository.existsById(new FollowId(follower, followee));
    }

    // 매개변수로 ID를 줄 때와, Member 객체를 줄 때의 쿼리 차이 확인 -> 차이가 없는 것으로 확인
    public List<Member> followerList(Member member) {
        return followRepository.findAllByFollower(member);
    }
}
