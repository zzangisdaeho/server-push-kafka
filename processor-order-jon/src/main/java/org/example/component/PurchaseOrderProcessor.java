package org.example.component;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.example.model.OrderCompleted;
import org.example.model.Payment;
import org.example.model.PurchaseOrder;
import org.example.utils.JsonDeserializer;
import org.example.utils.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 주문 처리 processor
 */
@Component
public class PurchaseOrderProcessor {

    // Purchase Order Message serde
    private Serde<PurchaseOrder> purchaseOrderSerde = Serdes.serdeFrom( new JsonSerializer<>(),  new JsonDeserializer<>(PurchaseOrder.class));

    // Payment Message 2 serde
     private Serde<Payment> paymentSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Payment.class));

    // Order process serde
    private Serde<OrderCompleted> orderCompletedSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(OrderCompleted.class));

    @Autowired
    void orderPaymentJoinPipeLine(StreamsBuilder streamsBuilder) {

        KStream<String, PurchaseOrder> orderKStream = streamsBuilder
                .stream("order-topic", Consumed.with(Serdes.String(), purchaseOrderSerde));

        KStream<String, Payment> paymentKStream = streamsBuilder
                .stream("payment-topic", Consumed.with(Serdes.String(), paymentSerde));


        orderKStream.join(paymentKStream,
                orderPaymentJoiner,
                JoinWindows.of(Duration.ofMinutes(5)),
                Joined.with(Serdes.String(), purchaseOrderSerde,paymentSerde))
                //.peek((k, v) -> { log.info("############################################ joined : {} ", v);});
                .to("shipping-topic", Produced.with(Serdes.String(), orderCompletedSerde));

    }

    /**
     * Order + Payment 를 join 하여 Order Completed 만듬
     */
    private ValueJoiner<PurchaseOrder, Payment, OrderCompleted> orderPaymentJoiner
            = (order, payment) ->
            OrderCompleted.builder()
            .orderId(order.getOrderId())
            .order(order)
            .payment(payment)
            .build();

}
