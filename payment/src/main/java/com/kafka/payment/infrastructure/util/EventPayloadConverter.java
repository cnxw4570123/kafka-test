package com.kafka.payment.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventPayloadConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(String message, Class<T> clazz) {
        try {
            return objectMapper.readValue(message, clazz);
        } catch (JsonProcessingException e) {
            log.error("{} - 역직렬화 실패", clazz.getName());
            throw new RuntimeException(e);
        }
    }

    public static <T> String serialize(T data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error("{} - 직렬화 실패", data.getClass().getName());
            throw new RuntimeException(e);
        }
    }
}
