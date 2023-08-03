package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauth2AccountId(String accountId);

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "select m from Member m order by m.no desc ",
            countQuery = "select count(m) from Member m")
    Page<Member> findPaging(Pageable pageable);



}
