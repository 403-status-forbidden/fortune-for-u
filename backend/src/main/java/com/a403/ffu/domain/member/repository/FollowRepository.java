package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.Follow;
import com.ssafy.a403.domain.member.entity.FollowId;
import com.ssafy.a403.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("select m from Follow f left join Member m on f.id.followee = m where f.id.follower = :follower")
    List<Member> findAllByFollower(@Param("follower") Member follower);
}
