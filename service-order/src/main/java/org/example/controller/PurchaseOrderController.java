package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.PurchaseOrder;
import org.example.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 API
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    /**
     * 주문 서비스 reference
     */
    private final PurchaseOrderService purchaseOrderService;

    /**
     * 주문 생성 API
     * @param purchaseOrder 주문 생성 대상
     * @return 생성된 주문
     */
    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.create(purchaseOrder);
    }
}
