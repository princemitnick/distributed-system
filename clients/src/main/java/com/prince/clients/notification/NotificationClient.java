package com.prince.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "NOTIFICATION-SERVICE"
)
public interface NotificationClient {

    @PostMapping("/api/v1/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);


}
