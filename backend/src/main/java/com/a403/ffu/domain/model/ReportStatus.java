package com.ssafy.a403.domain.model;

public enum ReportStatus {
    NONE,     // ~ 상담 종료
    WAITING,  // gpt 결과 생성 ~ 상담가 수정 전 (수정 가능 상태)
    COMPLETE  // 상담가 수정 완료
}
