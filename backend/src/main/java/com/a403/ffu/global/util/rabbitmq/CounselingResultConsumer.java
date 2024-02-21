package com.ssafy.a403.global.util.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.a403.domain.reservation.service.CounselingReservationService;
import com.ssafy.a403.global.util.rabbitmq.dto.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounselingResultConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private final CounselingReservationService counselingReservationService;

    @RabbitListener(queues = "result_queue")
    public void handleResult(String message) throws Exception {
        // 메시지 수신
        ResultMessage resultMessage = objectMapper.readValue(message, ResultMessage.class);
        Long reservationId = resultMessage.getReservationId();
        String gptResult = resultMessage.getGptResult();
        log.info("메시지 수신 = {}", message);
        log.info("상담 결과 처리 중...");

        // Gpt 결과 처리
        counselingReservationService.handleGptResult(reservationId, gptResult);
        log.info("상담 결과 처리 완료...");
    }
}
