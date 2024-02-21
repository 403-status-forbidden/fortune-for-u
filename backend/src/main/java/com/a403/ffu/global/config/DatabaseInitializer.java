//package com.ssafy.a403.global.config;
//
//
//import com.ssafy.a403.domain.counselorform.entity.CounselorForm;
//import com.ssafy.a403.domain.counselorform.repository.CounselorFormRepository;
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import com.ssafy.a403.domain.model.CounselorType;
//import com.ssafy.a403.domain.model.PassState;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class DatabaseInitializer {
//
//    private final MemberRepository memberRepository;
//    private final CounselorFormRepository counselorFormRepository;
//
//    @PostConstruct
//    public void init() {
//        log.info("Dummy Data Initializing");
//
//        initCounselorForms();
//    }
//
//    public void initCounselorForms() {
//        List<CounselorType> counselorTypes = List.of(CounselorType.SAJU, CounselorType.TARO, CounselorType.BOTH);
//        List<PassState> passStates = List.of(PassState.PASS, PassState.WAITING, PassState.REJECT);
//
//        List<Member> members = List.of(
//                Member.builder().email("abc123@gmail.com").name("김김김").profileImage("image1.png").build(),
//                Member.builder().email("abc456gmail.com").name("박박박").profileImage("image2.png").build(),
//                Member.builder().email("abc789@gmail.com").name("최최최").profileImage("image3.png").build()
//        );
//        memberRepository.saveAll(members);
//
//        List<CounselorForm> counselorForms = IntStream.range(0, 18).mapToObj(i ->
//                        CounselorForm.builder()
//                                .counselorType(counselorTypes.get(i % 3))
//                                .major("major" + i)
//                                .career("career" + i)
//                                .intro("intro" + i)
//                                .address("address" + i)
//                                .phone("phone" + i)
//                                .passState(passStates.get(i % 3))
//                                .member(members.get(i % 3))
//                                .build())
//                .collect(Collectors.toList());
//        counselorFormRepository.saveAll(counselorForms);
//    }
//}
