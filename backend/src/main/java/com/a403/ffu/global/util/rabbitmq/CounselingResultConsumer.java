package com.a403.ffu.global.util.rabbitmq;

import com.a403.ffu.global.util.rabbitmq.dto.ResultMessage;
import com.a403.ffu.reservation.service.CounselingReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounselingResultConsumer {

    private final ObjectMapper objectMapper;

    private final CounselingReservationService counselingReservationService;

    @RabbitListener(queues = "result_queue")
    public void handleResult(String message) throws Exception {
        // 메시지 수신
        ResultMessage resultMessage = objectMapper.readValue(message, ResultMessage.class);
        Long reservationId = resultMessage.reservationId();
        String gptResult = resultMessage.gptResult();
        log.info("메시지 수신 = {}", message);
        log.info("상담 결과 처리 중...");

        // Gpt 결과 처리
        counselingReservationService.handleGptResult(reservationId, gptResult);
        log.info("상담 결과 처리 완료...");
    }
}
