package com.ufg.inf.notificationService.listener;

import com.ufg.inf.common.model.InventoryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @KafkaListener(topics = "inventory-events", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(InventoryEvent event) {

        String message = event.isSuccess()
            ? "Pedido " + event.getOrderId() + " processado com sucesso. Notificação enviada."
            : "Pedido " + event.getOrderId() + " falhou por falta de estoque. Notificação enviada.";

        System.out.println(message);
    }
}
