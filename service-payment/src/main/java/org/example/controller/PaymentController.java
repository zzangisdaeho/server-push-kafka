package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.Payment;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 결제 API
 */
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    /**
     * payment server 의 reference
     */
    private final PaymentService paymentService;

    /***
     * 결제 실행
     * @param payment 결제 정보
     * @return 결제 완료
     */
    @PostMapping
    public Payment create(@RequestBody Payment payment) {
        return paymentService.create(payment);
    }
}
