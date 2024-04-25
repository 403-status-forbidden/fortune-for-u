package com.a403.ffu.member.repository;

import com.a403.ffu.member.entity.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauth2AccountId(String accountId);

    @Query("select m from Member m left join fetch m.counselor left join fetch m.roles where m.no = :id")
    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "select m from Member m order by m.no desc ",
            countQuery = "select count(m) from Member m")
    Page<Member> findPaging(Pageable pageable);
}
