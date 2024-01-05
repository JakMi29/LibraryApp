package com.example.kafka.service;

import com.example.kafka.domain.EmailMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;
public class CustomDeserializer implements Deserializer<EmailMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public EmailMessage deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            System.out.println("11111111111111111111");
            var m= objectMapper.readValue(new String(data, StandardCharsets.UTF_8), EmailMessage.class);
            System.out.println("22222222222222222222");
            return m;
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to EmailMessage");
        }
    }

    @Override
    public void close() {
    }
}