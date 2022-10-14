package org.example.model;

import lombok.*;

import java.util.List;

/**
 * 결제 객체 샘플
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class Payment {

    // 결제 ID
    private String paymentId;

    // 주문 ID
    private String orderId;

    // 금액
    private double amount;

}
