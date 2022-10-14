package org.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 배송 알림 서비스
 */
@Service
public class ShippingNotificationService {
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    /**
     * emitter 의 list
     */
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * emitter 의 reference 로 event 를 구독 한다.
     * @return emitter 의 reference
     */
    public SseEmitter subscribe() {

        final SseEmitter emitter = new SseEmitter();

        this.emitters.add(emitter);

        emitter.onCompletion( new Runnable() {

            public void run() {

                emitters.remove(emitter);

            }

        });

        emitter.onTimeout( new Runnable() {

            public void run() {

                emitters.remove(emitter);

            }
        });
        return emitter;
    }

    /***
     * shipping event 리스너
     * @param orderCompleted 주문완료 ( shipping 대상 ) event
     */
    @KafkaListener(topics = "shipping-topic", groupId = "test-consumer-group-00")
    public void shippingNotificationListener(String orderCompleted) {
        // dead emitter list
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<SseEmitter> deadEmitters = new ArrayList<>();

        System.out.println("emitters = " + emitters);

        for(SseEmitter emitter : emitters ) {
            try {
                emitter.send(orderCompleted);
            } catch (Exception e) {
                // 오류 발생 시 dead emitter 로 넣음
                deadEmitters.add(emitter);
            }
        }
        // emitter 리스트에서 dead emitter 제거
        this.emitters.removeAll(deadEmitters);
    }

}
