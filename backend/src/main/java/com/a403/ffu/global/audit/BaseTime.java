package com.ssafy.a403.global.audit;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * JpaAuditing: 생성일, 수정일 등의 엔티티가 공통으로 가져야 할 컬럼들을 자동으로 넣어주는 기능(중복 코드 제거)
 * ref) https://webcoding-start.tistory.com/53
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass   // BaseTime을 상속하는 클래스가 created, lastUpdated를 인식할 수 있도록
@EntityListeners(AuditingEntityListener.class)  // 자동으로 값을 넣어주는 annotation
public class BaseTime {

    @CreatedDate
    @Column(columnDefinition = "datetime", updatable = false)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    private LocalDateTime lastUpdated;
}
