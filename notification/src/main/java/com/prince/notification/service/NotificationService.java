package com.prince.notification.service;

import com.prince.clients.notification.NotificationRequest;

public interface NotificationService {

    void send(NotificationRequest notificationRequest);
}
