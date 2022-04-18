package com.prince.notification.service;

import com.prince.clients.notification.NotificationRequest;
import com.prince.notification.model.Notification;
import com.prince.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements  NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification
                        .builder()
                        .sentAt(LocalDateTime.now())
                        .toCustomerId(notificationRequest.getCustomerId())
                        .toCustomerEmail(notificationRequest.getCustomerEmail())
                        .sender("Princemitnick")
                        .message(notificationRequest.getMessage())
                        .build()
        );
    }
}
