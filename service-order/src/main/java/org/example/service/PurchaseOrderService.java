package org.example.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator ;
import lombok.RequiredArgsConstructor;
import org.example.model.PurchaseOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * 주문 서비스
 */
@RequiredArgsConstructor
@Service
public class PurchaseOrderService {

    /**
     * Kafka Template
     */
    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 주문 event publish
     * @param purchaseOrder 주문정보
     * @return 주문정보
     */
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {

        purchaseOrder.setOrderId(UUID.randomUUID().toString());
        kafkaTemplate.send("order-topic", purchaseOrder.getOrderId(), purchaseOrder);
        return purchaseOrder;
    }
}
