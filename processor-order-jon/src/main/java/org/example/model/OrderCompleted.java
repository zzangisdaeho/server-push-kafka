package org.example.model;

import lombok.*;

/**
 * 주문 완료 Object
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class OrderCompleted {

    /**
     * 주문 ID
     */
    private String orderId;

    /**
     * 주문 내용
     */
    private Object order;

    /**
     * 결제 내용
     */
    private Object payment;
}
