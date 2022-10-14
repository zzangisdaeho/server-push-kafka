package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.ShippingNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Shipping Controller
 */
@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    /**
     * 배송 알림 service 의 reference
     */
    private final ShippingNotificationService shippingNotificationService;

    /**
     * Shipping notifications
     */
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    public SseEmitter subscribe() {
        return shippingNotificationService.subscribe();
    }
}
