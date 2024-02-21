package com.ssafy.a403.domain.counselorform.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.a403.domain.counselorform.entity.QCounselorForm.counselorForm;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CounselorFormRepositoryImpl implements CounselorFormRepositoryCustom{

    private final JPAQueryFactory query;

    @Override
    public Page<CounselorForm> findAllPaging(String filter, Pageable pageable){

        // list와 count를 분리한 이유: 쿼리를 따로 만들어서 최적화하기 위함 (요구하는 것이 다르기 때문!)
        List<CounselorForm> forms = getCounselorFormList(filter, pageable);
        Long count = getCounselorFormCount(filter);

        return new PageImpl<>(forms, pageable, count);
    }

    private Long getCounselorFormCount(String filter) {

        return query.select(counselorForm.count())
                .from(counselorForm)
                .where(eqPassState(filter))
                .fetchOne();
    }

    private List<CounselorForm> getCounselorFormList(String filter, Pageable pageable){

        return query.selectFrom(counselorForm)
                .where(eqPassState(filter)) // where 절을 먼저 수행함으로써 필터링 후, join하여 최적화
                .innerJoin(counselorForm.member)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(counselorForm.no.desc())   // 최근 등록한 순
                .fetch();
    }

    private BooleanExpression eqPassState(String passState) {
        if (passState.isBlank()){
            return null;
        }
        return counselorForm.passState.stringValue().eq(passState);
    }
}
