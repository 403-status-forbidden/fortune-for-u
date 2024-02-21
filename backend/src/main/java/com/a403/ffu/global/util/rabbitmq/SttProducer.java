package com.ssafy.a403.global.util.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.a403.global.util.rabbitmq.dto.SttRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SttProducer {

    private static final String STT_TASK_QUEUE = "stt_queue";
    private static final String GPT_TASK_QUEUE = "gpt_queue";
    private static final String RESULT_QUEUE = "result_queue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Queue createSttQueue() {
        return new Queue(STT_TASK_QUEUE);
    }

    @Bean
    public Queue createGptQueue() { return new Queue(GPT_TASK_QUEUE); }

    @Bean
    public Queue createResultQueue() { return new Queue(RESULT_QUEUE); }

    public void produceSttTask(Long reservationId, String audioFilePath) throws JsonProcessingException {

        final SttRequest sttRequest = SttRequest.builder()
                .reservationId(reservationId)
                .audioFilePath(audioFilePath)
                .build();

        String jsonSttRequest = objectMapper.writeValueAsString(sttRequest);
        rabbitTemplate.convertAndSend(STT_TASK_QUEUE, jsonSttRequest);
        log.info("Produce task message to stt_queue");
    }
}