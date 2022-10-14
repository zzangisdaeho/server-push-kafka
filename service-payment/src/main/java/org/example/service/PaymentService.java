package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Payment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 결제 서비스
 */
@RequiredArgsConstructor
@Service
public class PaymentService {

    /**
     * Kafka Template
     */
    private final KafkaTemplate<String, Object> kafkaTemplate;


    /**
     * 결제 수행 
     * @param payment 결제 정보
     * @return 결제 완료
     */
    public Payment create(Payment payment) {

        payment.setPaymentId(UUID.randomUUID().toString());
        kafkaTemplate.send("payment-topic", payment.getOrderId(), payment);
        return payment;
    }

}
