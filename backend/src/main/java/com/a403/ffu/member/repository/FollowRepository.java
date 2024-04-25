package com.a403.ffu.member.repository;

import com.a403.ffu.member.entity.Follow;
import com.a403.ffu.member.entity.FollowId;
import com.a403.ffu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    @Query("select m from Follow f left join Member m on f.id.followee = m where f.id.follower = :follower")
    List<Member> findAllByFollower(@Param("follower") Member follower);
}
