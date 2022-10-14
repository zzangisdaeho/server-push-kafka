package org.example.model;

import lombok.*;

import java.util.List;

/**
 * 주문 객체 샘플
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class PurchaseOrder {

    // 주문 ID
    private String orderId;
    // 주문 Item
    private List<String> items;
}
